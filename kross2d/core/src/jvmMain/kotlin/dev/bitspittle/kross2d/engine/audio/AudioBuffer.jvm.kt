package dev.bitspittle.kross2d.engine.audio

import dev.bitspittle.kross2d.core.event.Event
import dev.bitspittle.kross2d.core.event.EventEmitter
import dev.bitspittle.kross2d.core.event.ScopedObserver
import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.Disposer
import dev.bitspittle.kross2d.core.memory.setParent
import dev.bitspittle.kross2d.engine.audio.Audio.Companion.LOOP_CONTINUOUSLY
import kotlinx.coroutines.*
import java.io.BufferedInputStream
import java.io.InputStream
import java.util.concurrent.locks.ReentrantLock
import javax.sound.sampled.*
import javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED
import javax.sound.sampled.AudioSystem
import kotlin.concurrent.withLock
import kotlin.math.min

private suspend fun delayWhileAudioIsPausedOrUnavailable() = delay(50)

private fun SourceDataLine.setVolume(volume: Float) {
    check(volume in 0f..1f)
    if (!isControlSupported(FloatControl.Type.MASTER_GAIN)) return

    val gainControl = getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
    val range = gainControl.maximum - gainControl.minimum
    val gain = (range * volume) + gainControl.minimum
    gainControl.value = gain
}

internal actual sealed class AudioBuffer : Disposable() {
    protected val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    actual var volume: Float = 1f
        set(value) {
            val clamped = value.coerceIn(0f, 1f)
            if (field != clamped) {
                field = clamped
                onVolumeChanged()
            }
        }

    protected abstract fun onVolumeChanged()

    private class ConvertedAudioSource(sourceAudioStream: InputStream) {
        private val originalStream: AudioInputStream = AudioSystem.getAudioInputStream(BufferedInputStream(sourceAudioStream))
            .also { it.mark(Int.MAX_VALUE) }

        private val format = originalStream.format.let { sourceFormat ->
            // We want PCM, keeping the original sample rate and channels, but setting 16-bit depth.
            // 16-bit PCM is universally supported by audio hardware.
            AudioFormat(
                PCM_SIGNED,
                sourceFormat.sampleRate,
                16, // bits per channel
                sourceFormat.channels,
                sourceFormat.channels * 2, // 16 bits or 2 bytes per channel
                sourceFormat.sampleRate,
                false // PCM standard is little-endian
            )
        }

        private fun createConvertedStream(): AudioInputStream {
            return AudioSystem.getAudioInputStream(format, originalStream)
        }

        var stream: AudioInputStream = createConvertedStream()
            private set

        fun openNewLine(): SourceDataLine {
            return (AudioSystem.getLine(DataLine.Info(SourceDataLine::class.java, format)) as SourceDataLine)
                .also { it.open() }
        }

        fun reset() {
            originalStream.reset()
            stream = createConvertedStream()
        }
    }

    actual class Streaming(sourceAudioStream: InputStream) : AudioBuffer() {
        companion object {
            private const val STREAMING_BUFFER_SIZE_BYTES = 4096

            fun tryCreate(sourceAudioStream: InputStream): Streaming? {
                return try {
                    Streaming(sourceAudioStream)
                } catch (_: Exception) {
                    null
                }
            }
        }

        private val lock = ReentrantLock()

        private var playbackState = PlaybackState.STOPPED

        private val convertedAudioSource = ConvertedAudioSource(sourceAudioStream)
        private var line = convertedAudioSource.openNewLine()
        private var remainingLoops = 0

        private fun reset() {
            convertedAudioSource.reset()
        }

        override fun onVolumeChanged() {
            line.setVolume(volume)
        }

        init {
            onVolumeChanged()
            scope.launch {
                val buffer = ByteArray(STREAMING_BUFFER_SIZE_BYTES)
                var lastPlaybackState = PlaybackState.STOPPED
                while (!isDisposed) {
                    var delay = true
                    lock.withLock {
                        if (playbackState == PlaybackState.PLAYING) {
                            if (lastPlaybackState != PlaybackState.PLAYING) {
                                line.start()
                            }
                            val bytesRead = convertedAudioSource.stream.read(buffer, 0, buffer.size)
                            if (bytesRead > 0) {
                                line.write(buffer, 0, bytesRead)
                                delay = false
                            } else if (bytesRead < 0) {
                                if (remainingLoops > 0) {
                                    reset()
                                    if (remainingLoops != LOOP_CONTINUOUSLY) remainingLoops--
                                } else {
                                    playbackState = PlaybackState.STOPPED
                                }
                            }
                        } else {
                            if (lastPlaybackState == PlaybackState.PLAYING) {
                                line.stop()
                            }
                        }
                        lastPlaybackState = playbackState
                    }
                    if (delay) delayWhileAudioIsPausedOrUnavailable()
                }
            }
        }

        actual fun play(loopCount: Int) {
            lock.withLock {
                remainingLoops = loopCount
                if (playbackState == PlaybackState.PLAYING) {
                    reset()
                }
                playbackState = PlaybackState.PLAYING
            }
        }

        actual fun pause() {
            lock.withLock {
                playbackState = PlaybackState.PAUSED
            }
        }

        actual fun resume() {
            lock.withLock {
                if (playbackState == PlaybackState.PAUSED) playbackState = PlaybackState.PLAYING
            }
        }

        override fun onDisposed() {
            lock.withLock {
                playbackState = PlaybackState.STOPPED
                line.stop()
                line.close()
                convertedAudioSource.stream.close()
            }
        }
    }

    actual class InMemory(audioStream: InputStream) : AudioBuffer() {
        companion object {
            fun tryCreate(audioStream: InputStream): InMemory? {
                return try {
                    InMemory(audioStream)
                } catch (_: Exception) {
                    null
                }
            }
        }

        private val convertedAudioSource = ConvertedAudioSource(audioStream)
        private val audioData = convertedAudioSource.stream.readAllBytes()

        private val _volumeChanged = EventEmitter<Unit>()
        private val volumeChanged: Event<Unit> = _volumeChanged

        actual fun play(loopCount: Int) = Instance(this, loopCount)

        override fun onVolumeChanged() = _volumeChanged(Unit)

        actual class Instance actual constructor(
            private val buffer: InMemory,
            loopCount: Int
        ) : Disposable() {
            companion object {
                // We write in tiny chunks, so if a pause request comes in, we interrupt faster
                private const val WRITE_CHUNK_SIZE_BYTES = 64
            }
            private val lock = ReentrantLock()

            private val line = buffer.convertedAudioSource.openNewLine()
            private var isPlaying = true

            private var remainingLoops = loopCount

            actual var volume: Float = 1f
                set(value) {
                    val clamped = value.coerceIn(0f, 1f)
                    if (field != clamped) {
                        field = clamped
                        onVolumeChanged()
                    }
                }

            private fun onVolumeChanged() {
                line.setVolume(buffer.volume * volume)
            }

            init {
                this.setParent(buffer)
                onVolumeChanged()
                buffer.volumeChanged += ScopedObserver(this) { onVolumeChanged() }

                buffer.scope.launch {
                    var lastPlaying = false
                    var playbackPosition = 0
                    while (!isDisposed && playbackPosition < buffer.audioData.size) {
                        var bytesToWrite = 0
                        var delay = false
                        lock.withLock {
                            if (isPlaying) {
                                if (!lastPlaying) {
                                    // If here, we JUST started or resumed
                                    line.start()
                                }

                                val bytesRemaining = buffer.audioData.size - playbackPosition
                                bytesToWrite = min(WRITE_CHUNK_SIZE_BYTES, bytesRemaining)
                            } else {
                                if (lastPlaying) {
                                    // If here, we JUST paused
                                    line.stop()
                                }

                                delay = true
                            }

                            lastPlaying = isPlaying
                        }

                        if (bytesToWrite > 0) {
                            playbackPosition += line.write(buffer.audioData, playbackPosition, bytesToWrite)

                            if (playbackPosition >= buffer.audioData.size) {
                                if (remainingLoops > 0) playbackPosition = 0
                                if (remainingLoops != LOOP_CONTINUOUSLY) remainingLoops--
                            }
                        }

                        if (delay) delayWhileAudioIsPausedOrUnavailable()
                    }

                    line.drain()
                    Disposer.dispose(this@Instance)
                }
            }

            actual fun pause() = lock.withLock {
                isPlaying = false
            }

            actual fun resume() = lock.withLock {
                if (!isDisposed && !isPlaying) {
                    isPlaying = true
                }
            }

            override fun onDisposed() = lock.withLock {
                isPlaying = false
                line.stop()
                line.close()
            }
        }
    }
}
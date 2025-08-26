package dev.bitspittle.kross2d.engine.audio

import dev.bitspittle.kross2d.core.event.Event
import dev.bitspittle.kross2d.core.event.EventEmitter
import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.Disposer
import dev.bitspittle.kross2d.core.memory.setParent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.InputStream
import java.util.concurrent.locks.ReentrantLock
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine
import javax.sound.sampled.SourceDataLine
import kotlin.concurrent.withLock
import kotlin.math.min

private const val STREAMING_BUFFER_SIZE_BYTES = 4096

private suspend fun delayWhileAudioIsPausedOrUnavailable() = delay(50)

internal actual sealed class AudioBuffer : Disposable() {
    protected val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    actual class Streaming(sourceAudioStream: InputStream) : AudioBuffer() {
        companion object {
            fun tryCreate(sourceAudioStream: InputStream): Streaming? {
                return try {
                    Streaming(sourceAudioStream)
                } catch (_: Exception) {
                    null
                }
            }
        }

        private val lock = ReentrantLock()

        private var isPlaying = false

        private val convertedAudioInputStream: AudioInputStream
        private val line: SourceDataLine

        init {
            // Buffer the source input stream so that we can mark / reset it
            val audioInputStream = AudioSystem.getAudioInputStream(BufferedInputStream(sourceAudioStream))
            audioInputStream.mark(Int.MAX_VALUE)

            val sourceFormat = audioInputStream.format
            // We want PCM, keeping the original sample rate and channels, but setting 16-bit depth.
            // 16-bit PCM is universally supported by audio hardware.
            val targetFormat = AudioFormat(
                PCM_SIGNED,
                sourceFormat.sampleRate,
                16, // bits per channel
                sourceFormat.channels,
                sourceFormat.channels * 2, // 16 bits or 2 bytes per channel
                sourceFormat.sampleRate,
                false // PCM standard is little-endian
            )

            convertedAudioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream)
            line = (AudioSystem.getLine(DataLine.Info(SourceDataLine::class.java, targetFormat)) as SourceDataLine)
                .also { it.open() }
        }

        init {
            scope.launch {
                val buffer = ByteArray(STREAMING_BUFFER_SIZE_BYTES)
                var lastPlaying = false
                while (!disposed) {
                    var delay = true
                    lock.withLock {
                        if (isPlaying) {
                            if (!lastPlaying) {
                                line.start()
                            }
                            val bytesRead = convertedAudioInputStream.read(buffer, 0, buffer.size)
                            if (bytesRead > 0) {
                                line.write(buffer, 0, bytesRead)
                                delay = false
                            }
                        } else {
                            if (lastPlaying) {
                                line.stop()
                            }
                        }
                        lastPlaying = isPlaying
                    }
                    if (delay) delayWhileAudioIsPausedOrUnavailable()
                }
            }
        }

        actual fun play() {
            lock.withLock { isPlaying = true }
        }

        actual fun pause() {
            lock.withLock { isPlaying = false }
        }

        actual fun reset() {
            lock.withLock { convertedAudioInputStream.reset() }
        }

        override fun onDisposed() {
            lock.withLock {
                isPlaying = false
                line.stop()
                line.close()
                convertedAudioInputStream.close()
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

        private val audioData: ByteArray
        private val audioFormat: AudioFormat

        init {
            // 1. Get an AudioInputStream from the sound file.
            AudioSystem.getAudioInputStream(audioStream).use { audioInputStream ->
                val sourceFormat = audioInputStream.format
                val targetFormat = AudioFormat(
                    PCM_SIGNED,
                    sourceFormat.sampleRate,
                    16, // bits per channel
                    sourceFormat.channels,
                    sourceFormat.channels * 2, // 16 bits or 2 bytes per channel
                    sourceFormat.sampleRate,
                    false // PCM standard is little-endian
                )

                audioFormat = targetFormat

                val convertedAudioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream)
                audioData = convertedAudioInputStream.readAllBytes()
            }
        }

        actual fun play() = Instance(this)

        actual class Instance actual constructor(private val buffer: InMemory) : Disposable() {
            private val lock = ReentrantLock()

            private val line = (AudioSystem.getLine(DataLine.Info(SourceDataLine::class.java, buffer.audioFormat)) as SourceDataLine)
                .also { it.open() }
            private var playbackPosition = 0
            private var isPlaying = true

            private val _completed = EventEmitter<Unit>()
            actual val completed: Event<Unit> = _completed

            init {
                this.setParent(buffer)

                buffer.scope.launch {
                    var lastPlaying = false
                    while (playbackPosition < buffer.audioData.size) {
                        var delay = false
                        lock.withLock {
                            if (isPlaying) {
                                if (!lastPlaying) {
                                    // If here, we JUST started or resumed
                                    line.start()
                                }

                                val bytesRemaining = buffer.audioData.size - playbackPosition
                                val bytesToWrite = min(STREAMING_BUFFER_SIZE_BYTES, bytesRemaining)

                                if (bytesToWrite > 0) {
                                    val bytesWritten = line.write(buffer.audioData, playbackPosition, bytesToWrite)
                                    check(bytesWritten > 0) { "Expected to write $bytesToWrite bytes of audio, but nothing was written" }
                                    playbackPosition += bytesWritten
                                }
                            } else {
                                if (lastPlaying) {
                                    // If here, we JUST paused
                                    line.stop()
                                }

                                delay = true
                            }

                            lastPlaying = isPlaying
                        }

                        if (delay) delayWhileAudioIsPausedOrUnavailable()
                    }

                    line.drain()
                    _completed(Unit)
                    Disposer.dispose(this@Instance)
                }
            }

            actual fun pause() {
                lock.withLock { isPlaying = false }
            }

            actual fun resume() {
                lock.withLock {
                    if (!disposed && !isPlaying) {
                        isPlaying = true
                    }
                }
            }

            override fun onDisposed() {
                lock.withLock {
                    isPlaying = false
                    line.stop()
                    line.close()
                }
            }
        }
    }
}
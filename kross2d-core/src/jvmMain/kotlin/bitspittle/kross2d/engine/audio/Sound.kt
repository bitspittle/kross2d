package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.*
import bitspittle.kross2d.engine.audio.openal.AlException
import bitspittle.kross2d.engine.audio.openal.AlGlobalState
import bitspittle.kross2d.engine.audio.openal.AlBuffer
import bitspittle.kross2d.engine.audio.openal.AlSource
import bitspittle.kross2d.engine.audio.openal.wav.WavBuffer
import com.jogamp.openal.AL
import com.jogamp.openal.ALFactory
import java.io.InputStream

actual class SoundHandle(buffer: AlBuffer): Disposable {
    private val audioSource = AlSource()
        .apply { attachToBuffer(buffer) }
        .also { source -> Disposer.register(this, source) }

    private val al = ALFactory.getAL()
    private val isValid
        get() = audioSource.id >= 0 // Should be true until disposed

    fun play() {
        if (isValid) {
            al.alSourcePlay(audioSource.id)
        }
    }

    fun stop() {
        if (isValid) {
            al.alSourceStop(audioSource.id)
        }
    }

    fun pause() {
        if (isValid) {
            al.alSourcePause(audioSource.id)
        }
    }

    fun isStopped(): Boolean {
        if (!isValid) return true

        val stateOut = IntArray(1)
        al.alGetSourcei(audioSource.id, AL.AL_SOURCE_STATE, stateOut, 0)
        return stateOut[0] == AL.AL_STOPPED
    }
}

actual class Sound(stream: InputStream) : Disposable {
    companion object {
        fun tryCreate(stream: InputStream): Sound? {
            return try {
                Sound(stream)
            } catch (_: AlException) {
                null
            }
        }
    }

    private val audioBuffer: Box<AlBuffer>
    private val handles = mutableListOf<Box<SoundHandle>>()

    init {
        AlGlobalState.INSTANCE.inc()
        audioBuffer = Disposer.register(this, WavBuffer(stream))
    }

    actual fun play(): Box<SoundHandle> {
        disposeStoppedSounds()
        Disposer.register(audioBuffer, SoundHandle(audioBuffer.deref())).let { soundHandle ->
            handles.add(soundHandle)
            soundHandle.deref().play()
            return soundHandle
        }
    }

    actual fun stop(handle: Box<SoundHandle>?) {
        handles.forEach { if (handle == null || handle === it) it.deref().stop() }
        disposeStoppedSounds()
    }

    actual fun pause(handle: Box<SoundHandle>?) {
        disposeStoppedSounds()
        handles.forEach { if (handle == null || handle === it) it.deref().pause() }
    }

    actual fun resume(handle: Box<SoundHandle>?) {
        disposeStoppedSounds()
        handles.forEach { if (handle == null || handle === it) it.deref().play() }
    }

    /**
     * OpenAL doesn't have an easy way to get notified when a sound stops on its own (vs. the
     * caller stopping them manually), so we just lazily remove them any time the user interacts
     * with our API.
     */
    private fun disposeStoppedSounds() {
        handles.forEach { if (it.deref().isStopped()) Disposer.dispose(it) }
        handles.removeAll { it.disposed }
    }

    override fun dispose() {
        AlGlobalState.INSTANCE.dec()
    }
}
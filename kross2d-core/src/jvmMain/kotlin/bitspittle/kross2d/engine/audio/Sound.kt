package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.*
import bitspittle.kross2d.engine.audio.openal.AlException
import bitspittle.kross2d.engine.audio.openal.AlGlobalState
import bitspittle.kross2d.engine.audio.openal.AlBuffer
import bitspittle.kross2d.engine.audio.openal.AlSource
import bitspittle.kross2d.engine.audio.openal.wav.WavData
import com.jogamp.openal.AL
import com.jogamp.openal.ALFactory
import java.io.InputStream

actual class SoundHandle internal constructor(buffer: AlBuffer): Disposable() {
    private val audioSource = AlSource().setParent(buffer).apply { attachToBuffer(buffer) }

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

actual class Sound(stream: InputStream) : Disposable() {
    companion object {
        fun tryCreate(stream: InputStream): Sound? {
            return try {
                Sound(stream)
            } catch (_: AlException) {
                null
            }
        }
    }

    private val wavData: WavData
    private val handles = mutableListOf<SoundHandle>()

    init {
        AlGlobalState.INSTANCE.inc()
        wavData = WavData(stream).setParent(this)
    }

    actual fun play(): SoundHandle {
        disposeStoppedSounds()
        wavData.alBuffer.let { buffer ->
            val handle = SoundHandle(buffer).setParent(wavData)
            handles.add(handle)
            handle.play()
            return handle
        }
    }

    actual fun stop(handle: SoundHandle?) {
        handles.forEach { if (handle == null || handle === it) it.stop() }
        disposeStoppedSounds()
    }

    actual fun pause(handle: SoundHandle?) {
        disposeStoppedSounds()
        handles.forEach { if (handle == null || handle === it) it.pause() }
    }

    actual fun resume(handle: SoundHandle?) {
        disposeStoppedSounds()
        handles.forEach { if (handle == null || handle === it) it.play() }
    }

    /**
     * OpenAL doesn't have an easy way to get notified when a sound stops on its own (vs. the
     * caller stopping them manually), so we just lazily remove them any time the user interacts
     * with our API.
     */
    private fun disposeStoppedSounds() {
        handles.forEach { if (it.isStopped()) Disposer.dispose(it) }
        handles.removeAll { it.disposed }
    }

    override fun onDisposed() {
        AlGlobalState.INSTANCE.dec()
    }
}
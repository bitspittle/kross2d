package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.Box
import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.Disposer
import bitspittle.kross2d.core.memory.deref
import bitspittle.kross2d.engine.dom.clearSource
import org.w3c.dom.Audio

actual class SoundHandle(audio: Audio): Disposable {
    // Delegate all work to audioHandle, which also handles music
    internal val innerHandler = AudioHandle(audio)

    init {
        Disposer.register(this, innerHandler)
    }
}

actual class Sound(path: String): Disposable {
    val jsAudio = Audio(path)
    private val handles = mutableListOf<Box<SoundHandle>>()

    actual fun play(): Box<SoundHandle> {
        return Disposer.register(this, SoundHandle(jsAudio)).also { soundHandle ->
            handles.add(soundHandle)
            soundHandle.deref {
                it.innerHandler.jsAudioPtr.onended = { handles.remove(soundHandle) }
                it.innerHandler.play()
            }
        }
    }

    actual fun stop(handle: Box<SoundHandle>?) {
        handles.forEach { if (handle == null || handle == it) it.deref().innerHandler.stop() }
    }

    actual fun pause(handle: Box<SoundHandle>?) {
        handles.forEach { if (handle == null || handle == it) it.deref().innerHandler.pause() }
    }

    actual fun resume(handle: Box<SoundHandle>?) {
        handles.forEach { if (handle == null || handle == it) it.deref().innerHandler.resume() }
    }

    override fun dispose() {
        jsAudio.clearSource()
    }
}


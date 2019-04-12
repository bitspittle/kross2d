package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.Box
import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.Disposer
import org.w3c.dom.Audio

actual class SoundHandle(jsAudio: Audio): Disposable {
    internal val jsAudioPtr = (jsAudio.cloneNode() as Audio)

    internal fun play() {
        if (jsAudioPtr.ended) return
        jsAudioPtr.play()
    }

    internal fun stop() {
        jsAudioPtr.currentTime = Double.MAX_VALUE
    }

    internal fun pause() {
        if (jsAudioPtr.ended) return
        jsAudioPtr.pause()
    }

    internal fun resume() {
        if (jsAudioPtr.ended) return
        jsAudioPtr.play()
    }

    override fun dispose() {
        stop()
    }
}

actual class Sound(path: String): Disposable {
    val jsAudio = Audio(path)
    private val handles = mutableListOf<Box<SoundHandle>>()

    actual fun play(): Box<SoundHandle> {
        return Disposer.register(this, SoundHandle(jsAudio)).also { soundHandle ->
            handles.add(soundHandle)
            soundHandle.deref {
                it.jsAudioPtr.onended = { handles.remove(soundHandle) }
                it.play()
            }
        }
    }

    actual fun stop(handle: Box<SoundHandle>?) {
        handles.forEach { if (handle == null || handle == it) it.deref().stop() }
    }

    actual fun pause(handle: Box<SoundHandle>?) {
        handles.forEach { if (handle == null || handle == it) it.deref().pause() }
    }

    actual fun resume(handle: Box<SoundHandle>?) {
        handles.forEach { if (handle == null || handle == it) it.deref().resume() }
    }
}


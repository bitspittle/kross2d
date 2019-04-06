package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.Disposable
import org.w3c.dom.Audio

actual class SoundHandle(jsAudio: Audio) {
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
}

actual class Sound(private var path: String): Disposable {
    val jsAudio = Audio(path)
    private val handles = mutableListOf<SoundHandle>()

    actual fun play(): SoundHandle {
        return SoundHandle(jsAudio)
            .apply {
                handles.add(this)
                jsAudioPtr.onended = { handles.remove(this) }
            }
            .also { it.play() }
    }

    actual fun stop(handle: SoundHandle?) {
        handles.forEach { if (handle == null || handle == it) it.stop() }
    }

    actual fun pause(handle: SoundHandle?) {
        handles.forEach { if (handle == null || handle == it) it.pause() }
    }

    actual fun resume(handle: SoundHandle?) {
        handles.forEach { if (handle == null || handle == it) it.resume() }
    }

    override fun dispose() {
        stop()
    }
}


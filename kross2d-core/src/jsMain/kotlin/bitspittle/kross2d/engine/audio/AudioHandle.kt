package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.engine.dom.clearSource
import org.w3c.dom.Audio

class AudioHandle(jsAudio: Audio): Disposable {
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
        jsAudioPtr.clearSource()
    }
}
package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.Disposer
import bitspittle.kross2d.engine.dom.clearSource
import org.w3c.dom.Audio

actual class Music(path: String) : Disposable() {
    val jsAudio = Audio(path)
    private val audioHandle = AudioHandle(jsAudio)

    init {
        Disposer.register(this, audioHandle)
    }

    actual fun play() {
        audioHandle.play()
    }

    actual fun stop() {
        audioHandle.stop()
    }

    actual fun pause() {
        audioHandle.pause()
    }

    actual fun resume() {
        audioHandle.resume()
    }

    override fun onDisposed() {
        jsAudio.clearSource()
    }
}
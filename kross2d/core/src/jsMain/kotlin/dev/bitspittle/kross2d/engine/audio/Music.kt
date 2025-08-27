package dev.bitspittle.kross2d.engine.audio

import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.setParent
import dev.bitspittle.kross2d.engine.dom.clearSource
import org.w3c.dom.Audio

actual class Music(path: String) : Disposable() {
    val jsAudio = Audio(path)
    private val audioHandle = AudioHandle(jsAudio).setParent(this)

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
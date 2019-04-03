package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.Disposable
import org.w3c.dom.Audio

actual class Sound(path: String): Disposable {
    val jsAudio = Audio(path)

    actual fun play() {
        jsAudio.currentTime = 0.0
        jsAudio.play()
    }
}
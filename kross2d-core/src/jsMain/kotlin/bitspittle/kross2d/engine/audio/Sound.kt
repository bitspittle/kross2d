package bitspittle.kross2d.engine.audio

import org.w3c.dom.Audio

actual class Sound(path: String) {
    val jsAudio = Audio(path)

    actual fun play() {
        jsAudio.currentTime = 0.0
        jsAudio.play()
    }
}
//package dev.bitspittle.kross2d.engine.audio
//
//import dev.bitspittle.kross2d.core.memory.Disposable
//import dev.bitspittle.kross2d.engine.dom.clearSource
//import org.w3c.dom.Audio
//
//internal class AudioHandle(jsAudio: Audio): Disposable() {
//    internal val jsAudioPtr = (jsAudio.cloneNode() as Audio)
//
//    internal fun play() {
//        if (jsAudioPtr.ended) return
//        jsAudioPtr.play()
//    }
//
//    internal fun stop() {
//        jsAudioPtr.currentTime = Double.MAX_VALUE
//    }
//
//    internal fun pause() {
//        if (jsAudioPtr.ended) return
//        jsAudioPtr.pause()
//    }
//
//    internal fun resume() {
//        if (jsAudioPtr.ended) return
//        jsAudioPtr.play()
//    }
//
//    override fun onDisposed() {
//        stop()
//        jsAudioPtr.clearSource()
//    }
//}
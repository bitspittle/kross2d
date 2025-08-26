//package dev.bitspittle.kross2d.engine.audio
//
//import dev.bitspittle.kross2d.core.memory.Disposable
//import dev.bitspittle.kross2d.core.memory.setParent
//import dev.bitspittle.kross2d.engine.dom.clearSource
//import org.w3c.dom.Audio
//
//actual class Music(path: String) : Disposable() {
//    val jsAudio = Audio(path)
//    private val audioHandle = AudioHandle(jsAudio).setParent(this)
//
//    private var _playbackState = PlaybackState.STOPPED
//    actual val playbackState get() = _playbackState
//
//    actual fun play() {
//        audioHandle.play()
//        _playbackState = PlaybackState.PLAYING
//    }
//
//    actual fun stop() {
//        audioHandle.stop()
//        _playbackState = PlaybackState.STOPPED
//    }
//
//    actual fun pause() {
//        audioHandle.pause()
//        _playbackState = PlaybackState.PAUSED
//    }
//
//    actual fun resume() {
//        check(_playbackState == PlaybackState.PAUSED) { "Attempting to resume a music stream that is not paused" }
//        audioHandle.resume()
//    }
//
//    override fun onDisposed() {
//        jsAudio.clearSource()
//    }
//
//}
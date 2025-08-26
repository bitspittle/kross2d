//package dev.bitspittle.kross2d.engine.audio
//
//import dev.bitspittle.kross2d.core.memory.Disposable
//import dev.bitspittle.kross2d.core.memory.setParent
//import dev.bitspittle.kross2d.engine.dom.clearSource
//import org.w3c.dom.Audio
//
//actual class SoundHandle(audio: Audio): Disposable() {
//    // Delegate all work to audioHandle, which also handles music
//    internal val innerHandler = AudioHandle(audio).setParent(this)
//}
//
//actual class Sound(path: String): Disposable() {
//    val jsAudio = Audio(path)
//    private val handles = mutableListOf<SoundHandle>()
//
//    actual fun play(): SoundHandle {
//        return SoundHandle(jsAudio).setParent(this).also { soundHandle ->
//            handles.add(soundHandle)
//            soundHandle.innerHandler.jsAudioPtr.onended = { handles.remove(soundHandle) }
//            soundHandle.innerHandler.play()
//        }
//    }
//
//    actual fun stop(handle: SoundHandle?) {
//        handles.forEach { if (handle == null || handle == it) it.innerHandler.stop() }
//    }
//
//    actual fun pause(handle: SoundHandle?) {
//        handles.forEach { if (handle == null || handle == it) it.innerHandler.pause() }
//    }
//
//    actual fun resume(handle: SoundHandle?) {
//        handles.forEach { if (handle == null || handle == it) it.innerHandler.resume() }
//    }
//
//    override fun onDisposed() {
//        jsAudio.clearSource()
//    }
//}
//

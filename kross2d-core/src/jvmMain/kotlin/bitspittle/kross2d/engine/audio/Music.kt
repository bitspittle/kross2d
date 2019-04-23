package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.setParent
import bitspittle.kross2d.engine.audio.openal.AlException
import bitspittle.kross2d.engine.audio.openal.AlGlobalState
import bitspittle.kross2d.engine.audio.openal.ogg.OggStreamer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

actual class Music(url: URL) : Disposable() {
    companion object {
        fun tryCreate(url: URL): Music? {
            return try {
                Music(url)
            } catch (_: AlException) {
                null
            }
        }
    }

    init {
        AlGlobalState.INSTANCE.inc()
    }

    private val oggStreamer = OggStreamer(url).setParent(this)

    actual fun play() {
        GlobalScope.launch {
            oggStreamer.playLoop()
        }
    }

    actual fun stop() {
        oggStreamer.stop()
    }

    actual fun pause() {
        oggStreamer.pause()
    }

    actual fun resume() {
        oggStreamer.resume()
    }

    override fun onDisposed() {
        AlGlobalState.INSTANCE.dec()
    }
}
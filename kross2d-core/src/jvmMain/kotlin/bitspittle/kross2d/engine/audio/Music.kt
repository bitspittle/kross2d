package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.Disposer
import bitspittle.kross2d.engine.audio.openal.AlException
import bitspittle.kross2d.engine.audio.openal.AlGlobalState
import bitspittle.kross2d.engine.audio.openal.ogg.OggStreamer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

actual class Music(url: URL) : Disposable {
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

    private val oggStreamer = Disposer.register(this, OggStreamer(url))

    actual fun play() {
        GlobalScope.launch {
            oggStreamer.deref().playLoop()
        }
    }

    actual fun stop() {
        oggStreamer.deref().stop()
    }

    actual fun pause() {
        oggStreamer.deref().pause()
    }

    actual fun resume() {
        oggStreamer.deref().resume()
    }

    override fun dispose() {
        AlGlobalState.INSTANCE.dec()
    }
}
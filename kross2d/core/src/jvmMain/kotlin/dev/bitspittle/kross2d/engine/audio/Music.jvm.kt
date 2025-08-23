package dev.bitspittle.kross2d.engine.audio

import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.setParent
import dev.bitspittle.kross2d.engine.audio.openal.AlException
import dev.bitspittle.kross2d.engine.audio.openal.AlGlobalState
import dev.bitspittle.kross2d.engine.audio.openal.ogg.OggStreamer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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
        AlGlobalState.Instance.inc()
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val oggStreamer = OggStreamer(url).setParent(this)

    actual fun play() {
        scope.launch {
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
        AlGlobalState.Instance.dec()
    }
}
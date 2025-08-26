package dev.bitspittle.kross2d.engine.audio

import dev.bitspittle.kross2d.core.event.Event
import dev.bitspittle.kross2d.core.event.EventEmitter
import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.Disposer
import dev.bitspittle.kross2d.core.memory.setParent
import dev.bitspittle.kross2d.engine.dom.clearSource
import org.w3c.dom.Audio

private fun Audio.stopAndClear() {
    currentTime = Double.MAX_VALUE
    clearSource()
}

internal actual sealed class AudioBuffer(path: String) : Disposable() {
    internal val jsAudio = Audio(path)

    override fun onDisposed() {
        jsAudio.stopAndClear()
    }

    actual class Streaming(path: String) : AudioBuffer(path) {
        actual fun play() {
            jsAudio.play()
        }

        actual fun pause() {
            jsAudio.pause()
        }

        actual fun reset() {
            jsAudio.currentTime = 0.0
        }
    }

    actual class InMemory(path: String) : AudioBuffer(path) {
        actual fun play(): Instance {
            return Instance(this)
        }

        actual class Instance actual constructor(buffer: InMemory) : Disposable() {
            private val _completed = EventEmitter<Unit>()
            actual val completed: Event<Unit> = _completed

            private val jsAudioInstance = (buffer.jsAudio.cloneNode() as Audio)

            init {
                this.setParent(buffer)

                jsAudioInstance.onended = {
                    _completed(Unit)
                    Disposer.dispose(this)
                }
                jsAudioInstance.play()
            }

            actual fun pause() {
                jsAudioInstance.pause()
            }

            actual fun resume() {
                jsAudioInstance.play()
            }

            override fun onDisposed() {
                jsAudioInstance.stopAndClear()
            }
        }
    }
}
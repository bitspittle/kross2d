package dev.bitspittle.kross2d.engine.audio

import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.Disposer
import dev.bitspittle.kross2d.core.memory.setParent
import dev.bitspittle.kross2d.engine.dom.clearSource
import org.w3c.dom.Audio as JsAudio

private fun JsAudio.stopAndClear() {
    currentTime = Double.MAX_VALUE
    clearSource()
}

internal actual sealed class AudioBuffer(path: String) : Disposable() {
    internal val jsAudio = JsAudio(path)

    actual var volume: Float
        get() = jsAudio.volume.toFloat()
        set(value) { jsAudio.volume = value.toDouble() }

    override fun onDisposed() {
        jsAudio.stopAndClear()
    }

    actual class Streaming(path: String) : AudioBuffer(path) {
        private var loopsRemaining = 0

        actual fun play(loopCount: Int) {
            loopsRemaining = loopCount
            jsAudio.onended = {
                if (!isDisposed && loopsRemaining > 0) {
                    if (loopsRemaining != Audio.LOOP_CONTINUOUSLY) loopsRemaining--
                    jsAudio.currentTime = 0.0
                    jsAudio.play()
                }
            }
            jsAudio.play()
        }

        actual fun pause() {
            jsAudio.pause()
        }

        actual fun resume() {
            jsAudio.play()
        }
    }

    actual class InMemory(path: String) : AudioBuffer(path) {
        actual fun play(loopCount: Int): Instance {
            return Instance(this, loopCount)
        }

        actual class Instance actual constructor(buffer: InMemory, loopCount: Int) : Disposable() {
            private val jsAudioInstance = (buffer.jsAudio.cloneNode() as JsAudio)

            actual var volume: Float
                get() = jsAudioInstance.volume.toFloat()
                set(value) { jsAudioInstance.volume = value.toDouble() }

            private var loopsRemaining = loopCount

            init {
                this.setParent(buffer)

                jsAudioInstance.onended = {
                    if (!isDisposed && loopsRemaining > 0) {
                        if (loopsRemaining != Audio.LOOP_CONTINUOUSLY) loopsRemaining--
                        jsAudioInstance.currentTime = 0.0
                        jsAudioInstance.play()
                    } else {
                        Disposer.dispose(this)
                    }
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
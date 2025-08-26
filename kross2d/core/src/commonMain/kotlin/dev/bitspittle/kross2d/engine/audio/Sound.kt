package dev.bitspittle.kross2d.engine.audio

import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.Disposer
import dev.bitspittle.kross2d.core.memory.register
import dev.bitspittle.kross2d.core.memory.setParent

/**
 * An audio sample, which is sound data loaded entirely into memory.
 *
 * This class is useful for playing quick sound effects. These are associated with files that often
 * end in .wav, .au, or .aiff
 */
class Sound internal constructor(private val buffer: AudioBuffer.InMemory):
    VolumeAdjustable, Disposable() {
    companion object {
        val DefaultVolumeGroup: VolumeGroup = VolumeGroup()
    }

    class PlayParams(
        val loopCount: Int = 0,
        val volumeGroup: VolumeGroup? = DefaultVolumeGroup,
        val pauseGroup: PauseGroup? = Audio.DefaultPauseGroup,
    )

    class Instance internal constructor(
        sound: Sound,
        private val bufferInstance: AudioBuffer.InMemory.Instance,
        private val volumeGroup: VolumeGroup?,
        private val pauseGroup: PauseGroup?,
    ) : VolumeAdjustable, Pausable, Disposable() {

        init {
            this.setParent(sound)
            Disposer.register(bufferInstance) {
                Disposer.dispose(this)
            }

            volumeGroup?.add(this)
            pauseGroup?.add(this)
        }

        override var volume: Float
            get() = bufferInstance.volume
            set(value) {
                bufferInstance.volume = value
            }

        override fun onDisposed() {
            volumeGroup?.remove(this)
            pauseGroup?.remove(this)
        }

        override fun pause() {
            bufferInstance.pause()
        }

        override fun resume() {
            bufferInstance.resume()
        }
    }

    var volumeGroup: VolumeGroup? = null
        set(value) {
            field?.remove(this)
            field = value
            value?.add(this)
        }

    override var volume: Float
        get() = buffer.volume
        set(value) { buffer.volume = value }


    init {
        buffer.setParent(this)
    }

    /**
     * Play this sound.
     *
     * If you make a second request to play while a sound is already playing, it will start up a
     * second copy of sound.
     */
    fun play(params: PlayParams = PlayParams()) = Instance(this, buffer.play(params.loopCount), params.volumeGroup, params.pauseGroup)

    override fun onDisposed() {
        volumeGroup?.remove(this)
    }
}


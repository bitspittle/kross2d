package dev.bitspittle.kross2d.engine.audio

import dev.bitspittle.kross2d.core.concurrency.synchronized
import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.setParent

/**
 * A music stream.
 *
 * Currently, .ogg and .mp3 formats are supported.
 */
class Music internal constructor(private val buffer: AudioBuffer.Streaming) : Pausable, VolumeAdjustable, Disposable() {
    companion object {
        val DefaultVolumeGroup: VolumeGroup = VolumeGroup()
    }

    class PlayParams(
        val loopCount: Int = Int.MAX_VALUE,
        val volumeGroup: VolumeGroup? = DefaultVolumeGroup,
        val pauseGroup: PauseGroup? = Audio.DefaultPauseGroup,
    )

    private val lock = Any()

    private var volumeGroup: VolumeGroup? = null
    private var pauseGroup: PauseGroup? = null

    override var volume: Float
        get() = buffer.volume
        set(value) { buffer.volume = value }

    init {
        buffer.setParent(this)
    }

    /**
     * Play the song from the beginning.
     *
     * Calling this on music that is already playing will restart it.
     */
    fun play(params: PlayParams = PlayParams()) = synchronized(lock) {
        volumeGroup?.remove(this)
        pauseGroup?.remove(this)

        volumeGroup = params.volumeGroup?.apply { add(this@Music) }
        pauseGroup = params.pauseGroup?.apply { add(this@Music) }

        buffer.play(loopCount = params.loopCount)
    }

    /**
     * Pause the song.
     *
     * Use [resume] to keep going from the paused location.
     */
    override fun pause() = synchronized(lock) {
        buffer.pause()
    }

    /**
     * Resume a song paused by [pause].
     */
    override fun resume() = synchronized(lock) {
        buffer.resume()
    }

    override fun onDisposed() {
        volumeGroup?.remove(this)
        pauseGroup?.remove(this)
    }
}


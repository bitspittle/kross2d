package dev.bitspittle.kross2d.engine.audio

import dev.bitspittle.kross2d.core.concurrency.synchronized
import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.setParent

/**
 * A music stream.
 *
 * Currently, .ogg and .mp3 formats are supported.
 */
class Music internal constructor(private val buffer: AudioBuffer.Streaming) : Disposable() {
    private var playbackState: PlaybackState = PlaybackState.PAUSED

    private val lock = Any()

    init {
        buffer.setParent(this)
    }

    /**
     * Play the song from the beginning.
     *
     * Calling this on music that is already playing will restart it.
     */
    fun play() = synchronized(lock) {
        if (playbackState == PlaybackState.PLAYING) return
        buffer.play()
        playbackState = PlaybackState.PLAYING
    }

    /**
     * Stop the song.
     *
     * Once stopped, a song can be restarted with [play].
     */
    fun stop()= synchronized(lock) {
        if (playbackState == PlaybackState.STOPPED) return
        buffer.pause()
        buffer.reset()
        playbackState = PlaybackState.STOPPED
    }

    /**
     * Pause the song.
     *
     * Use [resume] to keep going from the paused location.
     */
    fun pause() = synchronized(lock) {
        if (playbackState != PlaybackState.PLAYING) return
        buffer.pause()
        playbackState = PlaybackState.PAUSED
    }

    /**
     * Resume a song paused by [pause].
     *
     * @throws IllegalStateException if attempting to resume anytime besides a paused state.
     */
    fun resume() = synchronized(lock) {
        check(playbackState == PlaybackState.PAUSED) { "Attempting to resume a music stream that is not paused" }
        buffer.play()
        playbackState = PlaybackState.PLAYING
    }
}


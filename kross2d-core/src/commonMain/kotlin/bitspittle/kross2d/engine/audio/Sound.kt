package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.Disposable

/**
 * Represents a handle to an instance of a sound (as the same sound can be played multiple times
 * at the same time).
 */
expect class SoundHandle

/**
 * An audio sample, which is sound data loaded entirely into memory.
 *
 * This class is useful for playing quick sound effects. These are associated with files that often
 * end in .wav, .au, or .aiff
 */
expect class Sound : Disposable {
    /**
     * Play this sound.
     *
     * If you make a second request to play while a sound is already playing, it will start up a
     * second copy of sound. Use the returned [SoundHandle] to interact with individual instances
     * of playing sounds.
     */
    fun play(): SoundHandle

    /**
     * Stop (and thereby kill) any active sound. This is a no-op if the sound isn't playing.
     * Paused sounds will also be stopped.
     *
     * If [handle] is not specified, then all active instances of this sound are stopped.
     */
    fun stop(handle: SoundHandle? = null)

    /**
     * Pause the playing sound. This is a no-op if the sound isn't playing.
     *
     * If [handle] is not specified, then all active instances of this sound are paused.
     */
    fun pause(handle: SoundHandle? = null)

    /**
     * Resume any sound paused by [pause]. This is a no-op if the sound isn't paused.
     *
     * If [handle] is not specified, then all active instances of this sound are paused.
     */
    fun resume(handle: SoundHandle? = null)
}


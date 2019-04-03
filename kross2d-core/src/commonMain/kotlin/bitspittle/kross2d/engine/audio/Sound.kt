package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.Disposable

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
     * If you make a second call to this method while a sound is already playing, it will interrupt
     * the first call. If you would like to play the same sound multiple times at the same time,
     * you should create several [Sound] instances of the same file.
     */
    fun play()
}


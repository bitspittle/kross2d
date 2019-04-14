package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.Disposable

/**
 * A music stream.
 *
 * This class is associated with files that end in .ogg
 */
expect class Music : Disposable {
    fun play()
    fun stop()
    fun pause()
    fun resume()
}


package dev.bitspittle.kross2d.engine.audio

import dev.bitspittle.kross2d.core.concurrency.synchronized
import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.setParent

/**
 * An audio sample, which is sound data loaded entirely into memory.
 *
 * This class is useful for playing quick sound effects. These are associated with files that often
 * end in .wav, .au, or .aiff
 */
class Sound internal constructor(private val buffer: AudioBuffer.InMemory): Disposable() {
    private val instances = mutableListOf<AudioBuffer.InMemory.Instance>()

    private val lock = Any()

    init {
        buffer.setParent(this)
    }

    /**
     * Play this sound.
     *
     * If you make a second request to play while a sound is already playing, it will start up a
     * second copy of sound.
     */
    fun play() = synchronized(lock) {
        val newInstance = buffer.play()
        instances += newInstance
        newInstance.completed += {
            synchronized(lock) { instances -= newInstance }
        }
    }
}


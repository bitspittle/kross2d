package dev.bitspittle.kross2d.engine.audio

import dev.bitspittle.kross2d.core.concurrency.synchronized

interface Pausable {
    fun pause()
    fun resume()
}

class PauseGroup {
    private val members = mutableSetOf<Pausable>()
    private val lock = Any()

    internal fun add(pausable: Pausable) { synchronized(lock) { members.add(pausable) } }
    internal fun remove(pausable: Pausable) { synchronized(lock) { members.remove(pausable) } }

    fun pauseAll() = synchronized(lock) { members.forEach { it.pause() } }
    fun resumeAll() = synchronized(lock) { members.forEach { it.resume() } }
}
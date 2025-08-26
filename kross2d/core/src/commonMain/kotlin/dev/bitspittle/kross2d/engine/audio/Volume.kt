package dev.bitspittle.kross2d.engine.audio

import dev.bitspittle.kross2d.core.concurrency.synchronized

interface VolumeAdjustable {
    var volume: Float
}

class VolumeGroup {
    var volume: Float = 1f
        set(value) {
            synchronized(lock) {
                field = value
                members.forEach { it.volume = value }
            }
        }

    private val members = mutableSetOf<VolumeAdjustable>()
    private val lock = Any()

    internal fun add(volumeAdjustable: VolumeAdjustable) { synchronized(lock) { members.add(volumeAdjustable); volumeAdjustable.volume = volume } }
    internal fun remove(volumeAdjustable: VolumeAdjustable) { synchronized(lock) { members.remove(volumeAdjustable) } }
}
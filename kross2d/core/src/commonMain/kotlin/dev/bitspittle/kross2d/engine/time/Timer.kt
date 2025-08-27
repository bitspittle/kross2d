package dev.bitspittle.kross2d.engine.time

import dev.bitspittle.kross2d.core.time.Duration
import dev.bitspittle.kross2d.core.time.Instant
import dev.bitspittle.kross2d.core.time.MutableDuration

/**
 * An interface exposing timings around the last frame as well as helpful timing utility methods.
 */
interface Timer {
    /**
     * A measurement of time between the last frame starting and the current frame starting.
     *
     * A useful value to add to some accumulating `elapsed` timer or pass to classes expecting
     * some sort of tick update.
     */
    val lastFrame: Duration
}

internal class MutableTimer(override val lastFrame: MutableDuration = MutableDuration.zero()): Timer

/**
 * Shortcut for [Instant.now] for simplicity, allowing users of the *kross2d* API to find every
 * method they need from the context, and not having to remember one-off static methods here and there.
 */
@Suppress("UnusedReceiverParameter")
fun Timer.now() = Instant.now()

/**
 * Convenience method for measuring the duration of a block of code.
 */
inline fun Timer.measure(block: () -> Unit): Duration {
    val start = now()
    block()
    return now() - start
}
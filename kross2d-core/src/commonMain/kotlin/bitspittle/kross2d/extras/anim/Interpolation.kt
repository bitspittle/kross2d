package bitspittle.kross2d.extras.anim

import bitspittle.kross2d.core.math.HALF_PI
import kotlin.math.cos
import kotlin.math.sin

typealias Lerp<T> = (Float, T, T) -> T

@Suppress("UNUSED_PARAMETER") // Any unused parameters are there to match Lerp signature
object Lerps {
    /**
     * A stubbed implementation that always returns the [start] value, useful for default cases where
     * you don't want to lerp.
     */
    fun <T> noLerp(percent: Float, start: T, end: T): T = start

    fun linear(percent: Float, start: Float, end: Float): Float {
        return start + percent * (end - start)
    }

    fun easeOut(percent: Float, start: Float, end: Float): Float {
        // sin here goes from 0 -> 1, decelerating at the end
        return start + end * sin(percent * HALF_PI)
    }

    fun easeIn(percent: Float, start: Float, end: Float): Float {
        // cos here goes from 1 -> 0, accelerating at the beginning
        return start + end - (end * cos(percent * HALF_PI))
    }
}
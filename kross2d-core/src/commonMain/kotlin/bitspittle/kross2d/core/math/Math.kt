package bitspittle.kross2d.core.math

import kotlin.math.max
import kotlin.math.min

// Additional helper functions that aren't in the standard math library

fun Int.clamp(min: Int, max: Int) = max(min, min(max, this))
fun Long.clamp(min: Long, max: Long) = max(min, min(max, this))
fun Float.clamp(min: Float, max: Float) = max(min, min(max, this))
fun Double.clamp(min: Double, max: Double) = max(min, min(max, this))
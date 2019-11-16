package bitspittle.kross2d.core.math

import kotlin.math.PI
import kotlin.math.max
import kotlin.math.min

// Additional helper functions and constants that aren't in the standard math library

const val HALF_PI = (PI / 2).toFloat()
const val QUARTER_PI = (PI / 4).toFloat()

fun max(a: Short, b: Short) = if (a > b) a else b
fun min(a: Short, b: Short) = if (a < b) a else b

fun Short.clamp(min: Short, max: Short) = max(min, min(max, this))
fun Int.clamp(min: Int, max: Int) = max(min, min(max, this))
fun Long.clamp(min: Long, max: Long) = max(min, min(max, this))
fun Float.clamp(min: Float, max: Float) = max(min, min(max, this))
fun Double.clamp(min: Double, max: Double) = max(min, min(max, this))


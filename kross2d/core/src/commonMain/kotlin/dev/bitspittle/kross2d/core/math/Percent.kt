package dev.bitspittle.kross2d.core.math

import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * A class representing a percent value.
 */
interface Percent : Comparable<Percent> {
    val value: Float

    fun toPercent() = Percent(value)
    fun toMutablePercent() = MutablePercent(value)

    operator fun plus(other: Percent) = Percent(this.value + other.value)
    operator fun plus(other: Float) = Percent(this.value + other)
    operator fun minus(other: Percent) = Percent(this.value - other.value)
    operator fun minus(other: Float) = Percent(this.value - other)
    operator fun times(other: Percent) = Percent(this.value * other.value)
    operator fun times(other: Float) = Percent(this.value * other)
    operator fun div(other: Percent) = Percent(this.value / other.value)
    operator fun div(other: Float) = Percent(this.value / other)
    operator fun unaryMinus(): Percent = Percent(-this.value)
    override fun compareTo(other: Percent) = this.value.compareTo(other.value)

    fun toString(maxDecimalCount: Int): String {
        check(maxDecimalCount >= 0) { "maxDecimalCount cannot be negative" }

        val asWholePercentFormat = value * 100f // e.g. 0.5 -> 50.0

        // Next, we extend by the number of requested decimals, round, and then claw back
        // e.g. 50.0005 with 3 decimals -> 50000.5 --round--> 50001 -> 50.001
        val multiplier = 10f.pow(maxDecimalCount)
        val multiplierAsInt = multiplier.toInt()
        val multipliedAndRounded = (asWholePercentFormat * multiplier).roundToInt()

        val wholePart = multipliedAndRounded / multiplierAsInt
        val decimalPart = (multipliedAndRounded % multiplierAsInt)

        return "$wholePart.${decimalPart.toString().padStart(maxDecimalCount, '0')}".trimEnd('0').trimEnd('.') + "%"
    }
}

fun Percent(value: Float): Percent = MutablePercent(value)

/**
 * A mutable [Percent].
 *
 * @param transform An optional transformer that will get run every time a new value gets set. You can use this to
 *   ensure that your percent value is clamped between 0f and 1f, for example.
 */
class MutablePercent(value: Float, private val transform: (Float) -> Float = { it }) : Percent {
    override var value: Float = transform(value)
        set(value) { field = transform(value) }

    companion object {
        fun coerceIn(value: Float, minimumValue: Float, maximumValue: Float) =
            MutablePercent(value) { it.coerceIn(minimumValue, maximumValue) }
        fun coerceIn(value: Float, range: ClosedFloatingPointRange<Float>) = coerceIn(value, range.start, range.endInclusive)
        fun clamped(value: Float) = coerceIn(value, 0f, 1f)
    }

    fun set(other: Percent) { this.value = other.value }

    operator fun plusAssign(other: MutablePercent) { this.value += other.value }
    operator fun plusAssign(other: Float) { this.value += other }
    operator fun minusAssign(other: MutablePercent) { this.value -= other.value }
    operator fun minusAssign(other: Float) { this.value -= other }
    operator fun timesAssign(other: MutablePercent) { this.value *= other.value }
    operator fun timesAssign(other: Float) { this.value *= other }
    operator fun divAssign(other: MutablePercent) { this.value /= other.value }
    operator fun divAssign(other: Float) { this.value /= other }

    override fun equals(other: Any?): Boolean {
        return (other is MutablePercent && other.value == this.value)
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String = toString(2)
}
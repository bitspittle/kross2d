package dev.bitspittle.kross2d.core.time

import kotlin.math.max
import kotlin.math.min

/**
 * Read-only properties of a [Duration]
 */
abstract class ImmutableDuration : Comparable<ImmutableDuration> {
    abstract val nanos: Double
    open val micros: Double
        get() = nanos / 1000.0
    open val millis: Double
        get() = micros / 1000.0
    open val secs: Double
        get() = millis / 1000.0
    open val mins: Double
        get() = secs / 60.0
    fun isZero(): Boolean = nanos == 0.0

    fun copy() = Duration().apply { setFrom(this@ImmutableDuration) }

    operator fun plus(rhs: ImmutableDuration) = Duration(nanos + rhs.nanos)
    operator fun minus(rhs: ImmutableDuration) = Duration(nanos - rhs.nanos)
    operator fun times(value: Double) = Duration(nanos * value)
    operator fun div(value: Double) = Duration(nanos / value)
    operator fun unaryMinus() = Duration(-nanos)

    override fun compareTo(other: ImmutableDuration) = nanos.compareTo(other.nanos)

    override fun equals(other: Any?): Boolean {
        return if (other is ImmutableDuration) nanos == other.nanos else false
    }

    override fun hashCode(): Int {
        return nanos.hashCode()
    }

    override fun toString() = "Duration { ${nanos}ns }"
}

fun max(a: ImmutableDuration, b: ImmutableDuration) = if (a.nanos > b.nanos) a else b
fun min(a: ImmutableDuration, b: ImmutableDuration) = if (a.nanos < b.nanos) a else b

/**
 * A class which represents a time duration.
 */
class Duration
/**
 * Don't construct directly. Use [ofSeconds], [ofMinutes], [ofMillis], [ofMicros], [ofNanos],
 * [zero], or [copy] instead.
 */
internal constructor(override var nanos: Double = 0.0) : ImmutableDuration() {
    companion object {
        val Zero: ImmutableDuration = Duration()

        /**
         * A duration that essentially represents forever. This could be useful for parameters
         * that expect infinite timeouts, for example.
         */
        val MAX = object : ImmutableDuration() {
            override val nanos = Double.POSITIVE_INFINITY
            override val micros = Double.POSITIVE_INFINITY
            override val millis = Double.POSITIVE_INFINITY
            override val secs = Double.POSITIVE_INFINITY
            override val mins = Double.POSITIVE_INFINITY
        }

        /**
         * A duration that represents -MAX. This could be useful for when you are trying to
         * find the minimum duration value in a list, and want an initial value to compare
         * against.
         */
        val MIN = object : ImmutableDuration() {
            override val nanos = Double.NEGATIVE_INFINITY
            override val micros = Double.NEGATIVE_INFINITY
            override val millis = Double.NEGATIVE_INFINITY
            override val secs = Double.NEGATIVE_INFINITY
            override val mins = Double.NEGATIVE_INFINITY
        }

        fun zero(): Duration {
            return Duration()
        }

        fun ofNanos(nanos: Double) = Duration(nanos)
        fun ofMicros(micros: Double) = Duration().apply { this.micros = micros }
        fun ofMillis(millis: Double) = Duration().apply { this.millis = millis }
        fun ofSeconds(secs: Double) = Duration().apply { this.secs = secs }
        fun ofMinutes(mins: Double) = Duration().apply { this.mins = mins }

        fun ofNanos(nanos: Long) = ofNanos(nanos.toDouble())
        fun ofMicros(micros: Long) = ofMicros(micros.toDouble())
        fun ofMillis(millis: Long) = ofMillis(millis.toDouble())
        fun ofSeconds(secs: Long) = ofSeconds(secs.toDouble())
        fun ofMinutes(mins: Long) = ofMinutes(mins.toDouble())
    }

    override var micros: Double
        get() = super.micros
        set(value) {
            nanos = value * 1000.0
        }

    override var millis: Double
        get() = super.millis
        set(value) {
            micros = value * 1000.0
        }

    override var secs: Double
        get() = super.secs
        set(value) {
            millis = value * 1000.0
        }

    override var mins: Double
        get() = super.mins
        set(value) {
            secs = value * 60.0
        }

    fun setFrom(rhs: ImmutableDuration) {
        nanos = rhs.nanos
    }

    fun clampToMax(possibleMax: ImmutableDuration) {
        nanos = min(nanos, possibleMax.nanos)
    }

    fun clampToMin(possibleMin: ImmutableDuration) {
        nanos = max(nanos, possibleMin.nanos)
    }

    operator fun plusAssign(rhs: ImmutableDuration) {
        nanos += rhs.nanos
    }

    operator fun minusAssign(rhs: ImmutableDuration) {
        nanos -= rhs.nanos
    }

    operator fun timesAssign(value: Double) {
        nanos *= value
    }

    operator fun divAssign(value: Double) {
        nanos /= value
    }

    override fun toString(): String {
        return "${secs}s"
    }
}
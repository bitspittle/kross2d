package dev.bitspittle.kross2d.core.time

import dev.bitspittle.kross2d.core.time.MutableDuration.Companion.ofMicros
import dev.bitspittle.kross2d.core.time.MutableDuration.Companion.ofMillis
import dev.bitspittle.kross2d.core.time.MutableDuration.Companion.ofMinutes
import dev.bitspittle.kross2d.core.time.MutableDuration.Companion.ofNanos
import dev.bitspittle.kross2d.core.time.MutableDuration.Companion.ofSeconds
import dev.bitspittle.kross2d.core.time.MutableDuration.Companion.zero
import kotlin.math.max
import kotlin.math.min

internal interface DurationFactory<D: Duration> {
    fun ofNanos(nanos: Double): D
    fun ofMicros(micros: Double): D
    fun ofMillis(millis: Double): D
    fun ofSeconds(secs: Double): D
    fun ofMinutes(mins: Double): D

    fun ofNanos(nanos: Long): D = ofNanos(nanos.toDouble())
    fun ofMicros(micros: Long): D = ofMicros(micros.toDouble())
    fun ofMillis(millis: Long): D = ofMillis(millis.toDouble())
    fun ofSeconds(secs: Long): D = ofSeconds(secs.toDouble())
    fun ofMinutes(mins: Long): D = ofMinutes(mins.toDouble())
}

/**
 * A class which represents a time duration.
 */
interface Duration : Comparable<Duration> {
    companion object : DurationFactory<Duration> {
        val Zero: Duration = MutableDuration()

        /**
         * A duration that essentially represents forever. This could be useful for parameters
         * that expect infinite timeouts, for example.
         */
        val Max: Duration = MutableDuration(Double.POSITIVE_INFINITY)

        /**
         * A duration that represents -MAX. This could be useful for when you are trying to
         * find the minimum duration value in a list, and want an initial value to compare
         * against.
         */
        val Min: Duration = MutableDuration(Double.NEGATIVE_INFINITY)

        override fun ofNanos(nanos: Double): Duration = MutableDuration.ofNanos(nanos)
        override fun ofMicros(micros: Double): Duration = MutableDuration.ofMicros(micros)
        override fun ofMillis(millis: Double): Duration = MutableDuration.ofMillis(millis)
        override fun ofSeconds(secs: Double): Duration = MutableDuration.ofSeconds(secs)
        override fun ofMinutes(mins: Double): Duration = MutableDuration.ofMinutes(mins)
    }


    val nanos: Double
    val micros: Double
        get() = nanos / 1000.0
    val millis: Double
        get() = micros / 1000.0
    val secs: Double
        get() = millis / 1000.0
    val mins: Double
        get() = secs / 60.0
    fun isZero(): Boolean = nanos == 0.0

    fun toDuration() = Duration(nanos)
    fun toMutableDuration() = MutableDuration(nanos)

    operator fun plus(rhs: Duration) = Duration(nanos + rhs.nanos)
    operator fun minus(rhs: Duration) = Duration(nanos - rhs.nanos)
    operator fun times(value: Double) = Duration(nanos * value)
    operator fun div(value: Double) = Duration(nanos / value)
    operator fun unaryMinus() = Duration(-nanos)

    override fun compareTo(other: Duration) = nanos.compareTo(other.nanos)
}

fun max(a: Duration, b: Duration) = if (a.nanos > b.nanos) a else b
fun min(a: Duration, b: Duration) = if (a.nanos < b.nanos) a else b

/**
 * Don't construct directly. Use [ofSeconds], [ofMinutes], [ofMillis], [ofMicros], [ofNanos],
 * or [Duration.Zero] instead.
 */
private fun Duration(nanos: Double): Duration = MutableDuration(nanos)

class MutableDuration
/**
 * Don't construct directly. Use [ofSeconds], [ofMinutes], [ofMillis], [ofMicros], [ofNanos],
 * or [zero] instead.
 */
internal constructor(override var nanos: Double = 0.0) : Duration {
    companion object : DurationFactory<MutableDuration> {
        fun zero() = MutableDuration()

        override fun ofNanos(nanos: Double) = MutableDuration(nanos)
        override fun ofMicros(micros: Double) = MutableDuration().apply { this.micros = micros }
        override fun ofMillis(millis: Double) = MutableDuration().apply { this.millis = millis }
        override fun ofSeconds(secs: Double) = MutableDuration().apply { this.secs = secs }
        override fun ofMinutes(mins: Double) = MutableDuration().apply { this.mins = mins }
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

    fun setFrom(rhs: Duration) {
        nanos = rhs.nanos
    }

    fun clampToMax(possibleMax: Duration) {
        nanos = min(nanos, possibleMax.nanos)
    }

    fun clampToMin(possibleMin: Duration) {
        nanos = max(nanos, possibleMin.nanos)
    }

    operator fun plusAssign(rhs: Duration) {
        nanos += rhs.nanos
    }

    operator fun minusAssign(rhs: Duration) {
        nanos -= rhs.nanos
    }

    operator fun timesAssign(value: Double) {
        nanos *= value
    }

    operator fun divAssign(value: Double) {
        nanos /= value
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Duration) nanos == other.nanos else false
    }

    override fun hashCode(): Int {
        return nanos.hashCode()
    }

    override fun toString() = buildString {
        append("Duration { ")
        when {
            mins > 1 -> append("${mins}m")
            secs > 1 -> append("${secs}s")
            millis > 1 -> append("${millis}ms")
            micros > 1 -> append("${micros}μs")
            nanos > 1 -> append("${nanos}ns")
            else -> append("0")
        }
        append(" }")
    }
}

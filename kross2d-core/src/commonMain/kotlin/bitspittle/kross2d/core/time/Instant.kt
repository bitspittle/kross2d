package bitspittle.kross2d.core.time

/**
 * An immutable class which represents a moment in time.
 *
 * `Instant`s on their own are not useful, but you can subtract one from another to get
 * a `Duration`.
 *
 * @param nanos A value on the timescale of nanos. The absolute value has no meaning; the only
 * requirement is that two `Instant`s created over some elapsed time can be subtracted to
 * return a duration of that elapsed time.
 *
 * TODO: Convert to an inline class once that feature stabilizes
 */
class Instant(private var nanos: Long): Comparable<Instant> {
    companion object {
        fun now() = Instant(nowNs())
    }
    operator fun minus(rhs: Instant) = Duration((nanos - rhs.nanos).toDouble())
    override fun compareTo(other: Instant) = nanos.compareTo(other.nanos)

    override fun toString() = "Instant { ${nanos}ns }"
}

/**
 * Method that provides the current time in nanoseconds.
 *
 * The meaning of the absolute value doesn't matter; it just has to be consistent so two subsequent
 * calls to this method can be used to accurately measure the number of nanoseconds that passed
 * between them.
 */
internal expect fun nowNs(): Long
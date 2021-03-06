package bitspittle.kross2d.core.time

import bitspittle.truthish.assertThat
import kotlin.test.Test

private const val NS_IN_ONE_DAY = 1.0 * 24 * 60 * 60 * 1000 * 1000 * 1000
private const val US_IN_ONE_DAY = 1.0 * 24 * 60 * 60 * 1000 * 1000
private const val MS_IN_ONE_DAY = 1.0 * 24 * 60 * 60 * 1000
private const val SECS_IN_ONE_DAY = 1.0 * 24 * 60 * 60
private const val MINS_IN_ONE_DAY = 1.0 * 24 * 60

/**
 * Test [Duration] and [Instant] together, since they're relatively interdependent.
 */
class DurationInstantTest {
    @Test
    fun canConstructDuration() {
        run {
            val d = Duration.zero()
            assertThat(d.millis).isEqualTo(0.0)
            assertThat(d.secs).isEqualTo(0.0)
            assertThat(d.mins).isEqualTo(0.0)
            assertThat(d.isZero()).isTrue()
        }

        run {
            val d = Duration.ofNanos(NS_IN_ONE_DAY)
            assertThat(d.nanos).isEqualTo(NS_IN_ONE_DAY)
            assertThat(d.micros).isEqualTo(US_IN_ONE_DAY)
            assertThat(d.millis).isEqualTo(MS_IN_ONE_DAY)
            assertThat(d.secs).isEqualTo(SECS_IN_ONE_DAY)
            assertThat(d.mins).isEqualTo(MINS_IN_ONE_DAY)
            assertThat(d.isZero()).isFalse()
        }

        run {
            val d = Duration.ofMicros(US_IN_ONE_DAY)
            assertThat(d.nanos).isEqualTo(NS_IN_ONE_DAY)
            assertThat(d.micros).isEqualTo(US_IN_ONE_DAY)
            assertThat(d.millis).isEqualTo(MS_IN_ONE_DAY)
            assertThat(d.secs).isEqualTo(SECS_IN_ONE_DAY)
            assertThat(d.mins).isEqualTo(MINS_IN_ONE_DAY)
            assertThat(d.isZero()).isFalse()
        }

        run {
            val d = Duration.ofMillis(MS_IN_ONE_DAY)
            assertThat(d.nanos).isEqualTo(NS_IN_ONE_DAY)
            assertThat(d.micros).isEqualTo(US_IN_ONE_DAY)
            assertThat(d.millis).isEqualTo(MS_IN_ONE_DAY)
            assertThat(d.secs).isEqualTo(SECS_IN_ONE_DAY)
            assertThat(d.mins).isEqualTo(MINS_IN_ONE_DAY)
            assertThat(d.isZero()).isFalse()
        }

        run {
            val d = Duration.ofSeconds(SECS_IN_ONE_DAY)
            assertThat(d.nanos).isEqualTo(NS_IN_ONE_DAY)
            assertThat(d.micros).isEqualTo(US_IN_ONE_DAY)
            assertThat(d.millis).isEqualTo(MS_IN_ONE_DAY)
            assertThat(d.secs).isEqualTo(SECS_IN_ONE_DAY)
            assertThat(d.mins).isEqualTo(MINS_IN_ONE_DAY)
            assertThat(d.isZero()).isFalse()
        }

        run {
            val d = Duration.ofMinutes(MINS_IN_ONE_DAY)
            assertThat(d.nanos).isEqualTo(NS_IN_ONE_DAY)
            assertThat(d.micros).isEqualTo(US_IN_ONE_DAY)
            assertThat(d.millis).isEqualTo(MS_IN_ONE_DAY)
            assertThat(d.secs).isEqualTo(SECS_IN_ONE_DAY)
            assertThat(d.mins).isEqualTo(MINS_IN_ONE_DAY)
            assertThat(d.isZero()).isFalse()
        }

        run {
            val dSrc = Duration.ofNanos(NS_IN_ONE_DAY)
            val d = dSrc.copy()
            assertThat(d.nanos).isEqualTo(NS_IN_ONE_DAY)
            assertThat(d.micros).isEqualTo(US_IN_ONE_DAY)
            assertThat(d.millis).isEqualTo(MS_IN_ONE_DAY)
            assertThat(d.secs).isEqualTo(SECS_IN_ONE_DAY)
            assertThat(d.mins).isEqualTo(MINS_IN_ONE_DAY)
            assertThat(d.isZero()).isFalse()
        }
    }

    @Test
    fun minAndMaxWork() {
        val d1: ImmutableDuration = Duration.ofSeconds(1)
        val d5: ImmutableDuration = Duration.ofSeconds(5)
        val d9: ImmutableDuration = Duration.ofSeconds(9)

        assertThat(max(d1, d9)).isEqualTo(d9)
        assertThat(min(d1, d9)).isEqualTo(d1)

        assertThat(max(Duration.ZERO, Duration.MAX)).isEqualTo(Duration.MAX)
        assertThat(min(Duration.ZERO, Duration.MAX)).isEqualTo(Duration.ZERO)

        run {
            val d = d5.copy()
            d.clampToMin(d1)
            assertThat(d).isEqualTo(d5)
            d.clampToMax(d1)
            assertThat(d).isEqualTo(d1)
        }
        run {
            val d = d5.copy()
            d.clampToMax(d9)
            assertThat(d).isEqualTo(d5)
            d.clampToMin(d9)
            assertThat(d).isEqualTo(d9)
        }
    }

    @Test
    fun addAndSubtractDurations() {
        val d1: ImmutableDuration = Duration.ofSeconds(1)
        val d5: ImmutableDuration = Duration.ofSeconds(5)
        val d9: ImmutableDuration = Duration.ofSeconds(9)

        assertThat(d1 + d9).isEqualTo(Duration.ofSeconds(10))
        assertThat(d9 - d5).isEqualTo(Duration.ofSeconds(4))

        run {
            val d = d1.copy()
            d += d9
            assertThat(d.secs).isEqualTo(10.0)
        }
        run {
            val d = d5.copy()
            d -= d1
            assertThat(d.secs).isEqualTo(4.0)
        }
    }

    @Test
    fun durationsCanBeCompared() {
        val d1: ImmutableDuration = Duration.ofSeconds(1)
        val d5: ImmutableDuration = Duration.ofSeconds(5)
        val d9: ImmutableDuration = Duration.ofSeconds(9)

        assertThat(d1).isLessThan(d5)
        assertThat(d9).isGreaterThan(d5)
        assertThat(d1).isGreaterThan(Duration.ZERO)
        assertThat(d9).isLessThan(Duration.MAX)
        assertThat(Duration.ZERO).isGreaterThan(Duration.MIN)

        assertThat(Duration.zero()).isEqualTo(Duration.ZERO)
    }

    @Test
    fun durationsCanBeNegative() {
        val d1: ImmutableDuration = Duration.ofSeconds(1)
        val d5: ImmutableDuration = Duration.ofSeconds(5)

        assertThat(d1 - d5).isEqualTo(Duration.ofSeconds(-4))
    }

    @Test
    fun instantMinusInstantEqualsDuration() {
        val instantA = Instant(123)
        val instantB = Instant(1123)

        assertThat(instantB - instantA).isEqualTo(Duration.ofNanos(1000))
    }

    @Test
    fun durationCanBeNegated() {
        var duration = Duration.ofSeconds(123)

        duration = -duration
        assertThat(duration.secs).isEqualTo(-123.0)

        duration = -duration
        assertThat(duration.secs).isEqualTo(123.0)
    }

    @Test
    fun durationOverridesEqualsAndHashcode() {
        val durationSet = mutableSetOf(Duration.ofSeconds(3))
        assertThat(durationSet.contains(Duration.ofSeconds(3))).isTrue()
        assertThat(durationSet.contains(Duration.ofSeconds(2))).isFalse()
    }
}

package dev.bitspittle.kross2d.extras.anim

import com.varabyte.truthish.assertThat
import dev.bitspittle.kross2d.core.time.Duration
import kotlin.test.Test


class AnimTest {
    @Test
    fun canUsePrimaryConstructorToConstructAnim() {
        val intAnim = Anim(listOf(
                KeyFrame(1, Duration.ofMillis(100)),
                KeyFrame(2, Duration.ofMillis(200)),
                KeyFrame(3, Duration.ofMillis(300)),
                KeyFrame(4, Duration.ofMillis(400))))

        assertThat(intAnim.keyframes).hasSize(4)
        assertThat(intAnim.keyframes[1].duration).isEqualTo(Duration.ofMillis(200))
        assertThat(intAnim.keyframes[2].value).isEqualTo(3)
        assertThat(intAnim.duration.millis).isWithin(0.0).of(1000.0)
    }

    @Test
    fun canUseVarargsKeyFrameConstructorToConstructAnim() {
        val intAnim = Anim(
                KeyFrame(1, Duration.ofMillis(100)),
                KeyFrame(2, Duration.ofMillis(200)),
                KeyFrame(3, Duration.ofMillis(300)),
                KeyFrame(4, Duration.ofMillis(400)))

        assertThat(intAnim.keyframes).hasSize(4)
        assertThat(intAnim.keyframes[1].duration).isEqualTo(Duration.ofMillis(200))
        assertThat(intAnim.keyframes[2].value).isEqualTo(3)
        assertThat(intAnim.duration.millis).isWithin(0.0).of(1000.0)
    }

    @Test
    fun canUseVarargsValueConstructorToConstructAnim() {
        val intAnim = Anim(Duration.ofMillis(123), 1, 2, 3, 4)

        assertThat(intAnim.keyframes).hasSize(4)
        assertThat(intAnim.keyframes[1].duration).isEqualTo(Duration.ofMillis(123))
        assertThat(intAnim.keyframes[2].value).isEqualTo(3)
        assertThat(intAnim.duration.millis).isWithin(0.0).of(123.0 * 4)
    }

    @Test
    fun canResetAnimations() {
        val intAnim = Anim(Duration.ofMillis(100), 1, 2, 4, 9, 16, playStrategy = OneShotPlayStrategy())

        intAnim.elapse(Duration.ofMinutes(99))
        assertThat(intAnim.value).isEqualTo(16)

        intAnim.reset()
        assertThat(intAnim.value).isEqualTo(1)

        intAnim.elapse(Duration.ofMillis(250))
        assertThat(intAnim.value).isEqualTo(4)
    }

    @Test
    fun oneShotAnimWorksAsExpected() {
        val intAnim = Anim(Duration.ofMillis(100), 1, 2, 4, 9, 16, playStrategy = OneShotPlayStrategy())

        assertThat(intAnim.value).isEqualTo(1)

        intAnim.elapse(Duration.ofMillis(99))
        assertThat(intAnim.value).isEqualTo(1)

        intAnim.elapse(Duration.ofMillis(1))
        assertThat(intAnim.value).isEqualTo(2)

        intAnim.elapse(Duration.ofMillis(200))
        assertThat(intAnim.value).isEqualTo(9)

        intAnim.elapse(Duration.ofMillis(10000))
        assertThat(intAnim.value).isEqualTo(16)

        intAnim.reset()
        assertThat(intAnim.value).isEqualTo(1)
    }

    @Test
    fun loopingAnimWorksAsExpected() {
        val intAnim = Anim(Duration.ofMillis(100), 1, 2, 4, 9, 16, playStrategy = LoopingPlayStrategy())

        assertThat(intAnim.value).isEqualTo(1)

        intAnim.elapse(intAnim.duration - Duration.ofMillis(1))
        assertThat(intAnim.value).isEqualTo(16)

        intAnim.elapse(Duration.ofMillis(1))
        assertThat(intAnim.value).isEqualTo(1)

        intAnim.elapse(intAnim.duration / 2.0)
        assertThat(intAnim.value).isEqualTo(4)

        // Looping can handle very large elapse durations. Here we should end up back where we started
        intAnim.elapse(intAnim.duration * 5.0 )
        assertThat(intAnim.value).isEqualTo(4)

        intAnim.reset()
        assertThat(intAnim.value).isEqualTo(1)
    }

    @Test
    fun bouncingAnimWorksAsExpected() {
        val oneFrame = Duration.ofMillis(100)
        val intAnim = Anim(oneFrame, 1, 2, 3, 4, playStrategy = BouncingPlayStrategy())

        // Start with a mall bump to avoid landing exactly on a frame's end when coming back down
        intAnim.elapse(Duration.ofMillis(1))

        assertThat(intAnim.value).isEqualTo(1)
        intAnim.elapse(oneFrame)
        assertThat(intAnim.value).isEqualTo(2)
        intAnim.elapse(oneFrame)
        assertThat(intAnim.value).isEqualTo(3)
        intAnim.elapse(oneFrame)
        assertThat(intAnim.value).isEqualTo(4)
        intAnim.elapse(oneFrame)
        assertThat(intAnim.value).isEqualTo(4)
        intAnim.elapse(oneFrame)
        assertThat(intAnim.value).isEqualTo(3)

        // Reset also resets the bounce direction
        intAnim.reset()
        assertThat(intAnim.value).isEqualTo(1)
        intAnim.elapse(oneFrame)
        assertThat(intAnim.value).isEqualTo(2)

    }

    @Test
    fun animationsCanLerp() {
        val floatAnim = Anim(
            Duration.ofMillis(100),
            1f, 2f, 4f, 9f, 16f,
            defaultLerp = Lerps::linear,
            playStrategy = OneShotPlayStrategy()
        )

        assertThat(floatAnim.value).isWithin(0f).of(1f)

        floatAnim.elapse(Duration.ofMillis(50))
        assertThat(floatAnim.value).isWithin(0f).of(1.5f)

        floatAnim.elapse(Duration.ofMillis(200)) // Halfway between 4f and 9f
        assertThat(floatAnim.value).isWithin(0f).of(6.5f)

        floatAnim.elapse(Duration.ofMinutes(99)) // No lerp past the last frame
        assertThat(floatAnim.value).isWithin(0f).of(16f)

        floatAnim.reset()
        assertThat(floatAnim.value).isWithin(0f).of(1f)
    }

    @Test
    fun bouncingAnimsLerpInReverseAsWell() {
        val floatAnim = Anim(
            KeyFrame(0f, Duration.ofMillis(100), Lerps::linear),
            KeyFrame(10f, Duration.Zero),
            playStrategy = BouncingPlayStrategy()
        )

        floatAnim.elapse(Duration.ofMillis(20))
        assertThat(floatAnim.value).isWithin(0f).of(2f)

        floatAnim.elapse(floatAnim.duration) // Should go to the end of the anim and bounce back 20ms
        assertThat(floatAnim.value).isWithin(0f).of(8f)

        floatAnim.elapse(Duration.ofMillis(20))
        assertThat(floatAnim.value).isWithin(0f).of(6f)
    }
}
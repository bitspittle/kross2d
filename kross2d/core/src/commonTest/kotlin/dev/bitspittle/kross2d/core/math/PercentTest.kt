package dev.bitspittle.kross2d.core.math

import com.varabyte.truthish.assertThat
import kotlin.test.Test

class PercentTest {
    @Test
    fun basicTests() {
        val p1 = MutablePercent(0.25f)
        val p2 = MutablePercent(0.5f)

        assertThat(p1).isEqualTo(Percent(0.25f))
        assertThat(p1).isNotEqualTo(p2)

        assertThat(p1 + p2).isEqualTo(Percent(0.75f))
        assertThat(p2 - p1).isEqualTo(Percent(0.25f))
        assertThat(p1 * 2f).isEqualTo(Percent(0.5f))
        assertThat(p1 / 5f).isEqualTo(Percent(0.05f))

    }

    @Test
    fun testToString() {
        assertThat(MutablePercent(0.25f).toString()).isEqualTo("25%")

        assertThat(MutablePercent(1 / 3f).toString()).isEqualTo("33.33%")
        assertThat(MutablePercent(2 / 3f).toString()).isEqualTo("66.67%")

        assertThat(MutablePercent(0.0012345f).toString()).isEqualTo("0.12%")

        assertThat(MutablePercent(0.50005f).toString(0)).isEqualTo("50%")
        assertThat(MutablePercent(0.50005f).toString(1)).isEqualTo("50%")
        assertThat(MutablePercent(0.50005f).toString(2)).isEqualTo("50.01%")
        assertThat(MutablePercent(0.50005f).toString(3)).isEqualTo("50.005%")
        assertThat(MutablePercent(0.50005f).toString(4)).isEqualTo("50.005%")
    }

    @Test
    fun testClampedPercent() {
        assertThat(MutablePercent.clamped(0.25f).value).isEqualTo(0.25f)
        assertThat(MutablePercent.clamped(-0.25f).value).isEqualTo(0.0f)
        assertThat(MutablePercent.clamped(1.25f).value).isEqualTo(1.0f)

        val clamped = MutablePercent.clamped(0.25f)
        clamped -= 1.0f
        assertThat(clamped.value).isEqualTo(0.0f)

        clamped.value = 0.25f
        clamped *= 100f
        assertThat(clamped.value).isEqualTo(1.0f)
    }
}

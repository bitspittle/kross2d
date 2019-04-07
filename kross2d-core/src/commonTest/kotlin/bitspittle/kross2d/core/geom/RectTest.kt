package bitspittle.kross2d.core.geom

import bitspittle.kross2d.core.math.Pt2
import bitspittle.kross2d.core.math.Vec2
import bitspittle.truthish.assertThat
import kotlin.test.Test

class RectTest {
    @Test
    fun canConstructRectangles() {
        Rect(Pt2(10, 20), Vec2(90, 80)).let { rect ->
            assertThat(rect.x).isEqualTo(10f)
            assertThat(rect.y).isEqualTo(20f)
            assertThat(rect.w).isEqualTo(90f)
            assertThat(rect.h).isEqualTo(80f)
        }

        Rect(Vec2(90, 80)).let { rect ->
            assertThat(rect.x).isEqualTo(0f)
            assertThat(rect.y).isEqualTo(0f)
            assertThat(rect.w).isEqualTo(90f)
            assertThat(rect.h).isEqualTo(80f)
        }

        Rect(10, 20, 90, 80).let { rect ->
            assertThat(rect.x).isEqualTo(10f)
            assertThat(rect.y).isEqualTo(20f)
            assertThat(rect.w).isEqualTo(90f)
            assertThat(rect.h).isEqualTo(80f)
        }

        Rect(10f, 20f, 90f, 80f).let { rect ->
            assertThat(rect.x).isEqualTo(10f)
            assertThat(rect.y).isEqualTo(20f)
            assertThat(rect.w).isEqualTo(90f)
            assertThat(rect.h).isEqualTo(80f)
        }

        Rect(Rect(10, 20, 90, 80)).let { rect ->
            assertThat(rect.x).isEqualTo(10f)
            assertThat(rect.y).isEqualTo(20f)
            assertThat(rect.w).isEqualTo(90f)
            assertThat(rect.h).isEqualTo(80f)
        }

        Rect().let { rect ->
            assertThat(rect.x).isEqualTo(0f)
            assertThat(rect.y).isEqualTo(0f)
            assertThat(rect.w).isEqualTo(0f)
            assertThat(rect.h).isEqualTo(0f)
            assertThat(rect).isEqualTo(Rect.EMPTY)
        }
    }

    @Test
    fun canUpdateRectangles() {
        val r = Rect()

        r.x = 10f
        r.y = 20f
        r.w = 30f
        r.h = 40f
        assertThat(r).isEqualTo(Rect(10, 20, 30, 40))

        r.pos += Vec2(100, 100)
        r.size += Vec2(100, 100)
        assertThat(r).isEqualTo(Rect(110, 120, 130, 140))

        r.set(Rect.EMPTY)
        assertThat(r).isEqualTo(Rect())
    }

    @Test
    fun areaProducesExpectedValue() {
        val r = Rect(Vec2(10, 20))
        assertThat(r.area).isEqualTo(200f)
        r.pos += Vec2(100, 100)
        assertThat(r.area).isEqualTo(200f)
        r.size.y = 10f
        assertThat(r.area).isEqualTo(100f)
    }

    @Test
    fun rectCenterMethodsProduceExpectedValues() {
        val r = Rect(100, 200, 200, 100)
        assertThat(r.center).isEqualTo(Pt2(200, 250))

        assertThat(Vec2(50, 50).centerIn(r)).isEqualTo(Pt2(175, 225))
    }

    @Test
    fun rectIntersectionWorks() {
        val r1 = Rect(0, 0, 90, 100)
        val r2 = Rect(75, 75, 60, 100)
        val r3 = Rect(125, 125, 100, 100)

        assertThat(r1.intersects(r2)).isTrue()
        assertThat(r2.intersects(r3)).isTrue()
        assertThat(r1.intersects(r3)).isFalse()

        assertThat(r1.intersection(r2)).isEqualTo(Rect(75, 75, 15, 25))
        assertThat(r2.intersection(r3)).isEqualTo(Rect(125, 125, 10, 50))
        assertThat(r1.intersection(r3)).isEqualTo(Rect.EMPTY)

        assertThat(r1.intersects(r1))
        assertThat(r1.intersection(r1)).isEqualTo(r1)
    }
}
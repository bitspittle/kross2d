package bitspittle.kross2d.core.math

import bitspittle.truthish.assertThat
import kotlin.test.Test

class Pt2Vec2Test {
    @Test
    fun `can construct p2 and v2`() {
        run {
            // Pt2 default constructor
            val pt = Pt2()
            assertThat(pt).isEqualTo(Pt2.ZERO)
        }

        run {
            // Pt2 float constructor
            val pt = Pt2(10.0f, 20.0f)
            assertThat(pt.x).isEqualTo(10.0f)
            assertThat(pt.y).isEqualTo(20.0f)
        }

        run {
            // Pt2 int constructor
            val pt = Pt2(10, 20)
            assertThat(pt.x).isEqualTo(10.0f)
            assertThat(pt.y).isEqualTo(20.0f)
        }

        run {
            // Pt2 copy constructor
            val ptSrc = Pt2(10, 20)
            val pt = Pt2(ptSrc)
            assertThat(pt.x).isEqualTo(10.0f)
            assertThat(pt.y).isEqualTo(20.0f)
        }

        run {
            // Pt2 from Vec constructor
            val vec = Vec2(10, 20)
            val pt = Pt2(vec)
            assertThat(pt.x).isEqualTo(10.0f)
            assertThat(pt.y).isEqualTo(20.0f)
        }

        run {
            // Vec2 default constructor
            val vec = Vec2()
            assertThat(vec).isEqualTo(Vec2.ZERO)
        }

        run {
            // Vec2 float constructor
            val vec = Vec2(10.0f, 20.0f)
            assertThat(vec.x).isEqualTo(10.0f)
            assertThat(vec.y).isEqualTo(20.0f)
        }

        run {
            // Vec2 int constructor
            val vec = Vec2(10, 20)
            assertThat(vec.x).isEqualTo(10.0f)
            assertThat(vec.y).isEqualTo(20.0f)
        }

        run {
            // Vec2 copy constructor
            val vecSrc = Vec2(10, 20)
            val vec = Vec2(vecSrc)
            assertThat(vec.x).isEqualTo(10.0f)
            assertThat(vec.y).isEqualTo(20.0f)
        }

        run {
            // Vec2 from Vec constructor
            val pt = Pt2(10, 20)
            val vec = Vec2(pt)
            assertThat(vec.x).isEqualTo(10.0f)
            assertThat(vec.y).isEqualTo(20.0f)
        }
    }

    @Test
    fun `isZero works`() {
        run {
            val pt = Pt2()
            assertThat(pt.isZero()).isTrue()
            pt.x += 1
            assertThat(pt.isZero()).isFalse()
            pt.set(Pt2.ZERO)
            assertThat(pt.isZero()).isTrue()
            pt.y -= 1
            assertThat(pt.isZero()).isFalse()
            pt.set(Vec2.ZERO)
            assertThat(pt.isZero()).isTrue()
        }

        run {
            val vec = Vec2()
            assertThat(vec.isZero()).isTrue()
            vec.x += 1
            assertThat(vec.isZero()).isFalse()
            vec.set(Vec2.ZERO)
            assertThat(vec.isZero()).isTrue()
            vec.y -= 1
            assertThat(vec.isZero()).isFalse()
            vec.set(Pt2.ZERO)
            assertThat(vec.isZero()).isTrue()
        }
    }

    @Test
    fun `p2 plus v2 equals p2`() {
        val pt = Pt2(100, 200)
        val vec = Vec2(300, 400)

        assertThat(pt + vec).isEqualTo(Pt2(400, 600))
        pt += vec
        assertThat(pt).isEqualTo(Pt2(400, 600))
    }

    @Test
    fun `p2 minus v2 equals p2`() {
        val pt = Pt2(1000, 2000)
        val vec = Vec2(600, 1400)

        assertThat(pt - vec).isEqualTo(Pt2(400, 600))
        pt -= vec
        assertThat(pt).isEqualTo(Pt2(400, 600))
    }

    @Test
    fun `p2 minus p2 equals v2`() {
        val ptA = Pt2(1000, 2000)
        val ptB = Pt2(600, 1400)

        assertThat(ptA - ptB).isEqualTo(Vec2(400, 600))
    }

    @Test
    fun `p2 times v2 equals p2`() {
        val pt = Pt2(100, 200)
        val vec = Vec2(10, 10)

        assertThat(pt * vec).isEqualTo(Pt2(1000, 2000))
        pt *= vec
        assertThat(pt).isEqualTo(Pt2(1000, 2000))
    }

    @Test
    fun `p2 divided by v2 equals p2`() {
        val pt = Pt2(10000, 20000)
        val vec = Vec2(10, 10)

        assertThat(pt / vec).isEqualTo(Pt2(1000, 2000))
        pt /= vec
        assertThat(pt).isEqualTo(Pt2(1000, 2000))
    }

    @Test
    fun `p2 can be scaled by a scalar`() {
        run {
            val pt = Pt2(20, 30)
            assertThat(pt * 2f).isEqualTo(Pt2(40, 60))
            pt *= 2f
            assertThat(pt).isEqualTo(Pt2(40, 60))
        }

        run {
            val pt = Pt2(20, 30)
            assertThat(pt / 10f).isEqualTo(Pt2(2, 3))
            pt /= 10f
            assertThat(pt).isEqualTo(Pt2(2, 3))
        }
    }

    @Test
    fun `p2 can be negated`() {
        val pt = Pt2(20, 30)
        assertThat(-pt).isEqualTo(Pt2(-20, -30))
    }

    @Test
    fun `v2 plus v2 equals v2`() {
        val vecA = Vec2(100, 200)
        val vecB = Vec2(300, 400)

        assertThat(vecA + vecB).isEqualTo(Vec2(400, 600))
        vecA += vecB
        assertThat(vecA).isEqualTo(Vec2(400, 600))
    }

    @Test
    fun `v2 minus v2 equals v2`() {
        val vecA = Vec2(1000, 2000)
        val vecB = Vec2(600, 1400)

        assertThat(vecA - vecB).isEqualTo(Vec2(400, 600))
        vecA -= vecB
        assertThat(vecA).isEqualTo(Vec2(400, 600))
    }

    @Test
    fun `v2 times v2 equals v2`() {
        val vecA = Vec2(100, 200)
        val vecB = Vec2(10, 10)

        assertThat(vecA * vecB).isEqualTo(Vec2(1000, 2000))
        vecA *= vecB
        assertThat(vecA).isEqualTo(Vec2(1000, 2000))
    }

    @Test
    fun `v2 divided by v2 equals v2`() {
        val vecA = Vec2(10000, 20000)
        val vecB = Vec2(10, 10)

        assertThat(vecA / vecB).isEqualTo(Vec2(1000, 2000))
        vecA /= vecB
        assertThat(vecA).isEqualTo(Vec2(1000, 2000))
    }

    @Test
    fun `v2 can be scaled by a scalar`() {
        run {
            val vec = Vec2(20, 30)
            assertThat(vec * 2f).isEqualTo(Vec2(40, 60))
            vec *= 2f
            assertThat(vec).isEqualTo(Vec2(40, 60))
        }

        run {
            val vec = Vec2(20, 30)
            assertThat(vec / 10f).isEqualTo(Vec2(2, 3))
            vec /= 10f
            assertThat(vec).isEqualTo(Vec2(2, 3))
        }
    }

    @Test
    fun `v2 can be negated`() {
        val vec = Vec2(20, 30)
        assertThat(-vec).isEqualTo(Vec2(-20, -30))
    }

    @Test
    fun `v2 len and normalized`() {
        run {
            val vec = Vec2(6, 8)
            assertThat(vec.len2()).isEqualTo(100f)
            assertThat(vec.len()).isEqualTo(10f)
            assertThat(vec.normalized()).isEqualTo(Vec2(0.6f, 0.8f))
            assertThat(vec.normalized().len()).isEqualTo(1.0f)

            vec.normalize()
            assertThat(vec).isEqualTo(Vec2(0.6f, 0.8f))
            assertThat(vec.len()).isEqualTo(1.0f)
        }

        run {
            // Special case: 0-length vecs remain 0 when normalized
            val vec = Vec2()
            assertThat(vec.normalized()).isEqualTo(Vec2.ZERO)
            vec.normalize()
            assertThat(vec).isEqualTo(Vec2.ZERO)
        }
    }
}

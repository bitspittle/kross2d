package dev.bitspittle.kross2d.core.geom

import dev.bitspittle.kross2d.core.math.Pt2
import dev.bitspittle.kross2d.core.math.Vec2
import kotlin.math.max
import kotlin.math.min

interface Rect : Shape {
    companion object {
        val Empty: Rect = MutableRect()
    }

    val pos: Pt2
    val size: Vec2

    val x: Float get() = pos.x
    val y: Float get() = pos.y
    val w: Float get() = size.x
    val h: Float get() = size.y
    val x2: Float get() = x + w
    val y2: Float get() = y + h

    override val center: Pt2
        get() = Pt2(x + w / 2f, y + h / 2f)

    override val area: Float
        get() = size.x * size.y

    fun toMutableRect() = MutableRect(pos, size)

    override fun toBoundingRect() = this

    fun intersects(other: Rect): Boolean {
        return when {
            x + w < other.x -> false
            x > other.x + other.w -> false
            y + h < other.y -> false
            y > other.y + other.h -> false
            else -> true
        }
    }

    fun intersection(other: Rect): Rect {
        if (!intersects(other)) {
            return Empty
        }

        val x0 = max(x, other.x)
        val y0 = max(y, other.y)
        val x1 = min(x + w, other.x + other.w)
        val y1 = min(y + h, other.y + h)
        return Rect(x0, y0, x1 - x0, y1 - y0)
    }
}

fun Rect(pos: Pt2, size: Vec2): Rect = MutableRect(pos, size)
fun Rect(size: Vec2) = Rect(Pt2.Zero, size)
fun Rect(x: Float, y: Float, w: Float, h: Float) = Rect(Pt2(x, y), Vec2(w, h))
fun Rect(x: Int, y: Int, w: Int, h: Int) = Rect(Pt2(x, y), Vec2(w, h))

/**
 * Given a [Vec2], return a [Pt2] which would center it within the target rectangle.
 */
fun Vec2.centerIn(rect: Rect): Pt2 {
    val center = rect.center.toMutablePt2()
    center -= this / 2f
    return center
}

class MutableRect(pos: Pt2, size: Vec2) : Rect {
    constructor(): this(Pt2.Zero, Vec2.Zero)
    constructor(size: Vec2): this(Pt2.Zero, size)
    constructor(x: Float, y: Float, w: Float, h: Float): this(Pt2(x, y), Vec2(w, h))
    constructor(x: Int, y: Int, w: Int, h: Int): this(Pt2(x, y), Vec2(w, h))

    override val pos= pos.toMutablePt2()
    override val size = size.toMutableVec2()

    override var x: Float
        get() = super.x
        set(value) { pos.x = value }

    override var y: Float
        get() = super.y
        set(value) { pos.y = value }

    override var w: Float
        get() = super.w
        set(value) { size.x = value }

    override var h: Float
        get() = super.h
        set(value) { size.y = value }

    override var x2: Float
        get() = super.x2
        set(value) { size.x = value - x }

    override var y2: Float
        get() = super.y2
        set(value) { size.y = value - y }

    fun toRect() = Rect(pos, size)

    fun set(other: Rect) {
        pos.set(other.pos)
        size.set(other.size)
    }

    override fun equals(other: Any?): Boolean {
        if (other is Rect) {
            return pos == other.pos && size == other.size
        }
        return false
    }

    override fun hashCode(): Int {
        return arrayOf(pos, size).contentHashCode()
    }

    override fun toString() = "Rect { ($x, $y), ($w, $h) }"

}

package bitspittle.kross2d.core.geom

import bitspittle.kross2d.core.math.ImmutablePt2
import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.core.math.Pt2
import bitspittle.kross2d.core.math.Vec2
import kotlin.math.max
import kotlin.math.min

abstract class ImmutableRect {
    abstract val pos: ImmutablePt2
    abstract val size: ImmutableVec2

    open val x: Float
        get() = pos.x
    open val y: Float
        get() = pos.y
    open val w: Float
        get() = size.x
    open val h: Float
        get() = size.y

    val center: ImmutablePt2
        get() = Pt2(x + w / 2f, y + h / 2f)

    val area: Float
        get() = size.x * size.y

    fun intersects(other: ImmutableRect): Boolean {
        return when {
            x + w < other.x -> false
            x > other.x + other.w -> false
            y + h < other.y -> false
            y > other.y + other.h -> false
            else -> true
        }
    }

    fun intersection(other: ImmutableRect): Rect {
        if (!intersects(other)) {
            return Rect()
        }

        val x0 = max(x, other.x)
        val y0 = max(y, other.y)
        val x1 = min(x + w, other.x + other.w)
        val y1 = min(y + h, other.y + h)
        return Rect(x0, y0, x1 - x0, y1 - y0)
    }

    override fun equals(other: Any?): Boolean {
        if (other is ImmutableRect) {
            return pos == other.pos && size == other.size
        }
        return false
    }

    override fun hashCode(): Int {
        return pos.hashCode() + 31 * size.hashCode()
    }

    override fun toString() = "Rect { ($x, $y), ($w, $h) }"
}

/**
 * Given a [Vec2], return a [Pt2] which would center it within the target rectangle.
 */
fun ImmutableVec2.centerIn(rect: ImmutableRect): Pt2 {
    val center = Pt2(rect.center)
    center -= this / 2f
    return center
}

class Rect(pos: ImmutablePt2, size: ImmutableVec2) : ImmutableRect() {
    constructor(): this(Pt2.ZERO, Vec2.ZERO)
    constructor(size: ImmutableVec2): this(Pt2.ZERO, size)
    constructor(x: Float, y: Float, w: Float, h: Float): this(Pt2(x, y), Vec2(w, h))
    constructor(x: Int, y: Int, w: Int, h: Int): this(Pt2(x, y), Vec2(w, h))
    constructor(other: ImmutableRect): this(other.pos, other.size)

    companion object {
        val EMPTY: ImmutableRect = object : ImmutableRect() {
            override val pos = Pt2.ZERO
            override val size = Vec2.ZERO
        }
    }

    override val pos: Pt2 = Pt2(pos)
    override val size: Vec2 = Vec2(size)

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

    fun set(other: ImmutableRect) {
        pos.set(other.pos)
        size.set(other.size)
    }
}

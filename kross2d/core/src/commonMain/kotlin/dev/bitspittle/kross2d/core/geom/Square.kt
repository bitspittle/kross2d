package dev.bitspittle.kross2d.core.geom

import dev.bitspittle.kross2d.core.math.ImmutablePt2
import dev.bitspittle.kross2d.core.math.ImmutableVec2
import dev.bitspittle.kross2d.core.math.Pt2
import dev.bitspittle.kross2d.core.math.Vec2
import kotlin.math.max
import kotlin.math.min

abstract class ImmutableSquare : ImmutableShape {
    abstract val pos: ImmutablePt2
    abstract val side: Float

    open val x: Float
        get() = pos.x
    open val y: Float
        get() = pos.y
    open val x2: Float
        get() = x + side
    open val y2: Float
        get() = y + side


    override val center: ImmutablePt2
        get() = toBoundingRect().center

    override val area: Float
        get() = side * side

    override fun toBoundingRect() = Rect(x, y, side, side)

    override fun equals(other: Any?): Boolean {
        if (other is ImmutableSquare) {
            return pos == other.pos && side == other.side
        }
        return false
    }

    override fun hashCode(): Int {
        return pos.hashCode() + 31 * side.hashCode()
    }

    override fun toString() = "Square { ($x, $y), ($side, $side) }"
}

class Square(pos: ImmutablePt2, side: Float) : ImmutableSquare() {
    constructor(): this(Pt2.ZERO, 0f)
    constructor(side: Float): this(Pt2.ZERO, side)
    constructor(x: Float, y: Float, side: Float): this(Pt2(x, y), side)
    constructor(x: Int, y: Int, side: Int): this(Pt2(x, y), side.toFloat())
    constructor(other: ImmutableSquare): this(other.pos, other.side)

    companion object {
        val EMPTY: ImmutableSquare = object : ImmutableSquare() {
            override val pos = Pt2.ZERO
            override val side: Float = 0f
        }
    }

    override val pos: Pt2 = Pt2(pos)
    override var side: Float = side

    override var x: Float
        get() = super.x
        set(value) { pos.x = value }

    override var y: Float
        get() = super.y
        set(value) { pos.y = value }

    override var x2: Float
        get() = super.x2
        set(value) { side = value - x }

    override var y2: Float
        get() = super.y2
        set(value) { side = value - y }

    fun set(other: ImmutableSquare) {
        pos.set(other.pos)
        side = other.side
    }
}

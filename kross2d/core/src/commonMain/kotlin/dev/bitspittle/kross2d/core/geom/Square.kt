package dev.bitspittle.kross2d.core.geom

import dev.bitspittle.kross2d.core.math.Pt2

interface Square : Shape {
    companion object {
        val Empty: Square = MutableSquare()
    }

    val pos: Pt2
    val side: Float

    val x: Float get() = pos.x
    val y: Float get() = pos.y
    val x2: Float get() = x + side
    val y2: Float get() = y + side

    override val center: Pt2
        get() = toBoundingRect().center

    override val area: Float
        get() = side * side

    fun toSquare() = Square(pos, side)
    fun toMutableSquare() = MutableSquare(x, y, side)

    override fun toBoundingRect() = Rect(x, y, side, side)
}

fun Square(pos: Pt2, side: Float): Square = MutableSquare(pos, side)
fun Square(side: Float) = Square(Pt2.Zero, side)
fun Square(x: Float, y: Float, side: Float) = Square(Pt2(x, y), side)
fun Square(x: Int, y: Int, side: Int) = Square(Pt2(x, y), side.toFloat())

class MutableSquare(pos: Pt2, side: Float) : Square {
    constructor(): this(Pt2.Zero, 0f)
    constructor(side: Float): this(Pt2.Zero, side)
    constructor(x: Float, y: Float, side: Float): this(Pt2(x, y), side)
    constructor(x: Int, y: Int, side: Int): this(Pt2(x, y), side.toFloat())

    override val pos = pos.toMutablePt2()
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

    fun set(other: Square) {
        pos.set(other.pos)
        side = other.side
    }

    override fun equals(other: Any?): Boolean {
        if (other is Square) {
            return pos == other.pos && side == other.side
        }
        return false
    }

    override fun hashCode(): Int {
        return arrayOf(pos, side).contentHashCode()
    }

    override fun toString() = "Square { ($x, $y), ($side, $side) }"
}

package dev.bitspittle.kross2d.core.math

interface Pt2 {
    companion object {
        val Zero: Pt2 = MutablePt2()
    }

    val x: Float
    val y: Float

    fun isZero() = x == 0f && y == 0f

    fun toPt2() = Pt2(x, y)
    fun toMutablePt2() = MutablePt2(x, y)

    operator fun component1() = x
    operator fun component2() = y

    operator fun unaryMinus() = Pt2(-this.x, -this.y)
    operator fun plus(rhs: Vec2) = Pt2(this.x + rhs.x, this.y + rhs.y)
    operator fun minus(rhs: Vec2) = Pt2(this.x - rhs.x, this.y - rhs.y)
    operator fun minus(rhs: Pt2) = Vec2(this.x - rhs.x, this.y - rhs.y)
    operator fun times(rhs: Vec2) = Pt2(this.x * rhs.x, this.y * rhs.y)
    operator fun times(value: Float) = Pt2(this.x * value, this.y * value)
    operator fun div(rhs: Vec2) = Pt2(this.x / rhs.x, this.y / rhs.y)
    operator fun div(value: Float) = Pt2(this.x / value, this.y / value)
}

fun Pt2(x: Float, y: Float): Pt2 = MutablePt2(x, y)
fun Pt2(x: Int, y: Int) = Pt2(x.toFloat(), y.toFloat())
/**
 * Normally, points and vectors are intentionally different, but occasionally it's useful to
 * convert between the two.
 */
fun Pt2(vec: Vec2) = Pt2(vec.x, vec.y)

class MutablePt2(override var x: Float, override var y: Float) : Pt2 {
    constructor() : this(0f, 0f)
    constructor(x: Int, y: Int) : this(x.toFloat(), y.toFloat())
    /**
     * Normally, points and vectors are intentionally different, but occasionally it's useful to
     * convert between the two.
     */
    constructor(vec: Vec2) : this(vec.x, vec.y)

    fun set(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun set(x: Int, y: Int) {
        this.x = x.toFloat()
        this.y = y.toFloat()
    }

    fun set(other: Pt2) {
        x = other.x
        y = other.y
    }

    fun set(other: Vec2) {
        x = other.x
        y = other.y
    }

    operator fun plusAssign(rhs: Vec2) {
        this.x += rhs.x
        this.y += rhs.y
    }

    operator fun minusAssign(rhs: Vec2) {
        this.x -= rhs.x
        this.y -= rhs.y
    }

    operator fun timesAssign(rhs: Vec2) {
        this.x *= rhs.x
        this.y *= rhs.y
    }

    operator fun timesAssign(value: Float) {
        this.x *= value
        this.y *= value
    }

    operator fun divAssign(rhs: Vec2) {
        this.x /= rhs.x
        this.y /= rhs.y
    }

    operator fun divAssign(value: Float) {
        this.x /= value
        this.y /= value
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Pt2) x == other.x && y == other.y else false
    }

    override fun hashCode(): Int {
        return arrayOf(x, y).contentHashCode()
    }

    override fun toString() = "Pt2 { ($x, $y) }"
}

package bitspittle.kross2d.core.math

interface ImmutablePt2 {
    val x: Float
    val y: Float

    fun isZero() = x == 0f && y == 0f

    operator fun unaryMinus() = Pt2(-this.x, -this.y)
    operator fun plus(rhs: ImmutableVec2) = Pt2(this.x + rhs.x, this.y + rhs.y)
    operator fun minus(rhs: ImmutableVec2) = Pt2(this.x - rhs.x, this.y - rhs.y)
    operator fun minus(rhs: ImmutablePt2) = Vec2(this.x - rhs.x, this.y - rhs.y)
    operator fun times(rhs: ImmutableVec2) = Pt2(this.x * rhs.x, this.y * rhs.y)
    operator fun times(value: Float) = Pt2(this.x * value, this.y * value)
    operator fun div(rhs: ImmutableVec2) = Pt2(this.x / rhs.x, this.y / rhs.y)
    operator fun div(value: Float) = Pt2(this.x / value, this.y / value)
}

data class Pt2(override var x: Float, override var y: Float) : ImmutablePt2 {
    constructor() : this(0f, 0f)
    constructor(x: Int, y: Int) : this(x.toFloat(), y.toFloat())
    constructor(pt: ImmutablePt2) : this(pt.x, pt.y)
    /**
     * Normally, points and vectors are intentionally different, but occasionally it's useful to
     * convert between the two.
     */
    constructor(vec: ImmutableVec2) : this(vec.x, vec.y)

    companion object {
        val ZERO: ImmutablePt2 = Pt2()
    }

    fun set(other: ImmutablePt2) {
        x = other.x
        y = other.y
    }

    fun set(other: ImmutableVec2) {
        x = other.x
        y = other.y
    }

    operator fun plusAssign(rhs: ImmutableVec2) {
        this.x += rhs.x
        this.y += rhs.y
    }

    operator fun minusAssign(rhs: ImmutableVec2) {
        this.x -= rhs.x
        this.y -= rhs.y
    }

    operator fun timesAssign(rhs: ImmutableVec2) {
        this.x *= rhs.x
        this.y *= rhs.y
    }

    operator fun timesAssign(value: Float) {
        this.x *= value
        this.y *= value
    }

    operator fun divAssign(rhs: ImmutableVec2) {
        this.x /= rhs.x
        this.y /= rhs.y
    }

    operator fun divAssign(value: Float) {
        this.x /= value
        this.y /= value
    }
}

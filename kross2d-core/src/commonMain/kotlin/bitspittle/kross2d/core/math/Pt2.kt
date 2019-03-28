package bitspittle.kross2d.core.math

abstract class ImmutablePt2 {
    abstract val x: Float
    abstract val y: Float

    fun isZero() = x == 0f && y == 0f

    operator fun unaryMinus() = Pt2(-this.x, -this.y)
    operator fun plus(rhs: ImmutableVec2) = Pt2(this.x + rhs.x, this.y + rhs.y)
    operator fun minus(rhs: ImmutableVec2) = Pt2(this.x - rhs.x, this.y - rhs.y)
    operator fun minus(rhs: ImmutablePt2) = Vec2(this.x - rhs.x, this.y - rhs.y)
    operator fun times(rhs: ImmutableVec2) = Pt2(this.x * rhs.x, this.y * rhs.y)
    operator fun times(value: Float) = Pt2(this.x * value, this.y * value)
    operator fun div(rhs: ImmutableVec2) = Pt2(this.x / rhs.x, this.y / rhs.y)
    operator fun div(value: Float) = Pt2(this.x / value, this.y / value)

    override fun equals(other: Any?): Boolean {
        return if (other is ImmutablePt2) x == other.x && y == other.y else false
    }

    override fun hashCode(): Int {
        return x.hashCode() + 31 * y.hashCode()
    }

    override fun toString() = "Pt2 { ($x, $y) }"
}

class Pt2(override var x: Float, override var y: Float) : ImmutablePt2() {
    constructor() : this(0f, 0f)
    constructor(x: Int, y: Int) : this(x.toFloat(), y.toFloat())
    constructor(pt: ImmutablePt2) : this(pt.x, pt.y)
    /**
     * Normally, points and vectors are intentionally different, but occasionally it's useful to
     * convert between the two.
     */
    constructor(vec: ImmutableVec2) : this(vec.x, vec.y)

    companion object {
        val ZERO = object : ImmutablePt2() {
            override val x = 0f
            override val y = 0f
        }
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

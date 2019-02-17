package bitspittle.kross2d.core.math

import kotlin.math.sqrt

interface ImmutableVec2 {
    val x: Float
    val y: Float

    fun isZero() = x == 0f && y == 0f
    fun len2() = (x * x) + (y * y)
    fun len() = sqrt(len2())
    fun normalized() = if (isZero()) Vec2.ZERO else this / len()

    operator fun unaryMinus() = Vec2(-this.x, -this.y)
    operator fun plus(other: ImmutableVec2) = Vec2(this.x + other.x, this.y + other.y)
    operator fun minus(other: ImmutableVec2) = Vec2(this.x - other.x, this.y - other.y)
    operator fun times(other: ImmutableVec2) = Vec2(this.x * other.x, this.y * other.y)
    operator fun times(value: Float) = Vec2(this.x * value, this.y * value)
    operator fun div(other: ImmutableVec2) = Vec2(this.x / other.x, this.y / other.y)
    operator fun div(value: Float) = Vec2(this.x / value, this.y / value)
}

data class Vec2(override var x: Float, override var y: Float) : ImmutableVec2 {
    constructor() : this(0.0f, 0.0f)
    constructor(x: Int, y: Int) : this(x.toFloat(), y.toFloat())
    constructor(vec: ImmutableVec2) : this(vec.x, vec.y)
    /**
     * Normally, points and vectors are intentionally different, but occasionally it's useful to
     * convert between the two.
     */
    constructor(pt: ImmutablePt2) : this(pt.x, pt.y)

    companion object {
        val ZERO: ImmutableVec2 = Vec2()
    }

    fun set(other: ImmutablePt2) {
        x = other.x
        y = other.y
    }

    fun set(other: ImmutableVec2) {
        x = other.x
        y = other.y
    }

    fun normalize() {
        if (!isZero()) {
            val len = len()
            x /= len
            y /= len
        }
    }

    operator fun plusAssign(other: ImmutableVec2) {
        this.x += other.x
        this.y += other.y
    }

    operator fun minusAssign(other: ImmutableVec2) {
        this.x -= other.x
        this.y -= other.y
    }

    operator fun timesAssign(other: ImmutableVec2) {
        this.x *= other.x
        this.y *= other.y
    }

    operator fun timesAssign(value: Float) {
        this.x *= value
        this.y *= value
    }

    operator fun divAssign(other: ImmutableVec2) {
        this.x /= other.x
        this.y /= other.y
    }

    operator fun divAssign(value: Float) {
        this.x /= value
        this.y /= value
    }
}

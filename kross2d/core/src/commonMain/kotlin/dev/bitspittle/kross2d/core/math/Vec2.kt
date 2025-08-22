package dev.bitspittle.kross2d.core.math

import kotlin.math.sqrt

abstract class ImmutableVec2 {
    abstract val x: Float
    abstract val y: Float

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

    override fun equals(other: Any?): Boolean {
        return if (other is ImmutableVec2) x == other.x && y == other.y else false
    }

    override fun hashCode(): Int {
        return x.hashCode() + 31 * y.hashCode()
    }

    override fun toString() = "Vec2 { ($x, $y) }"
}

class Vec2(override var x: Float, override var y: Float) : ImmutableVec2() {
    constructor() : this(0.0f, 0.0f)
    constructor(x: Int, y: Int) : this(x.toFloat(), y.toFloat())
    constructor(vec: ImmutableVec2) : this(vec.x, vec.y)
    /**
     * Normally, points and vectors are intentionally different, but occasionally it's useful to
     * convert between the two.
     */
    constructor(pt: ImmutablePt2) : this(pt.x, pt.y)

    companion object {
        val ZERO = object : ImmutableVec2() {
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

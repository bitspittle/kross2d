@file:Suppress("FunctionName") // Intentional factory function names


package dev.bitspittle.kross2d.core.math

import kotlin.math.sqrt

interface Vec2 {
    companion object {
        val Zero: Vec2 = MutableVec2()
    }

    val x: Float
    val y: Float


    fun isZero() = x == 0f && y == 0f
    fun len2() = (x * x) + (y * y)
    fun len() = sqrt(len2())
    fun normalized() = if (isZero()) Zero else this / len()

    fun toMutableVec2() = MutableVec2(x, y)

    operator fun component1() = x
    operator fun component2() = y

    operator fun unaryMinus() = Vec2(-this.x, -this.y)
    operator fun plus(other: Vec2) = Vec2(this.x + other.x, this.y + other.y)
    operator fun minus(other: Vec2) = Vec2(this.x - other.x, this.y - other.y)
    operator fun times(other: Vec2) = Vec2(this.x * other.x, this.y * other.y)
    operator fun times(value: Float) = Vec2(this.x * value, this.y * value)
    operator fun div(other: Vec2) = Vec2(this.x / other.x, this.y / other.y)
    operator fun div(value: Float) = Vec2(this.x / value, this.y / value)
}

fun Vec2(x: Float, y: Float): Vec2 = MutableVec2(x, y)
fun Vec2(x: Int, y: Int): Vec2 = Vec2(x.toFloat(), y.toFloat())
/**
 * Normally, points and vectors are intentionally different, but occasionally it's useful to
 * convert between the two.
 */
fun Vec2(pt: Pt2) = Vec2(pt.x, pt.y)

class MutableVec2(override var x: Float, override var y: Float) : Vec2 {
    constructor() : this(0.0f, 0.0f)
    constructor(x: Int, y: Int) : this(x.toFloat(), y.toFloat())
    /**
     * Normally, points and vectors are intentionally different, but occasionally it's useful to
     * convert between the two.
     */
    constructor(pt: Pt2) : this(pt.x, pt.y)

    fun toVec2() = Vec2(x, y)

    fun set(other: Pt2) {
        x = other.x
        y = other.y
    }

    fun set(other: Vec2) {
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

    operator fun plusAssign(other: Vec2) {
        this.x += other.x
        this.y += other.y
    }

    operator fun minusAssign(other: Vec2) {
        this.x -= other.x
        this.y -= other.y
    }

    operator fun timesAssign(other: Vec2) {
        this.x *= other.x
        this.y *= other.y
    }

    operator fun timesAssign(value: Float) {
        this.x *= value
        this.y *= value
    }

    operator fun divAssign(other: Vec2) {
        this.x /= other.x
        this.y /= other.y
    }

    operator fun divAssign(value: Float) {
        this.x /= value
        this.y /= value
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Vec2) x == other.x && y == other.y else false
    }

    override fun hashCode(): Int {
        return arrayOf(x, y).contentHashCode()
    }

    override fun toString() = "Vec2 { ($x, $y) }"
}

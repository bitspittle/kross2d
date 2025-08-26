package dev.bitspittle.kross2d.core.geom

import dev.bitspittle.kross2d.core.math.MutablePt2
import dev.bitspittle.kross2d.core.math.Pt2
import kotlin.math.PI

interface Circle : Shape {
    companion object {
        val Empty: Circle = MutableCircle()
    }

    val radius: Float

    val x: Float
        get() = center.x
    val y: Float
        get() = center.y

    override val area: Float
        get() = (PI * radius * radius).toFloat()

    fun toMutableCircle() = MutableCircle(center, radius)

    override fun toBoundingRect() = Rect(center.x - radius, center.y - radius, 2 * radius, 2 * radius)

    fun intersects(other: Circle): Boolean {
        val radiiSum = other.radius + radius
        return (other.center - center).len2() < (radiiSum * radiiSum)
    }
}

fun Circle(center: Pt2, radius: Float): Circle = MutableCircle(center, radius)
fun Circle(radius: Float) = Circle(Pt2.Zero, radius)
fun Circle(x: Float, y: Float, radius: Float) = Circle(Pt2(x, y), radius)
fun Circle(x: Int, y: Int, radius: Int) = Circle(Pt2(x, y), radius.toFloat())

class MutableCircle(center: Pt2, radius: Float) : Circle {
    constructor(): this(Pt2.Zero, 0f)
    constructor(radius: Float): this(Pt2.Zero, radius)
    constructor(x: Float, y: Float, radius: Float): this(Pt2(x, y), radius)
    constructor(x: Int, y: Int, radius: Int): this(Pt2(x, y), radius.toFloat())

    override val center: MutablePt2 = center.toMutablePt2()
    override var radius: Float = radius

    override var x: Float
        get() = super.x
        set(value) { center.x = value }

    override var y: Float
        get() = super.y
        set(value) { center.y = value }

    fun toCircle() = Circle(center, radius)

    fun set(other: Circle) {
        center.set(other.center)
        radius = other.radius
    }

    override fun equals(other: Any?): Boolean {
        if (other is Circle) {
            return center == other.center && radius == other.radius
        }
        return false
    }

    override fun hashCode(): Int {
        return arrayOf(center, radius).contentHashCode()
    }

    override fun toString() = "Circle { ($x, $y), $radius }"
}

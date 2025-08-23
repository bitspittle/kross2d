package dev.bitspittle.kross2d.core.geom

import dev.bitspittle.kross2d.core.math.ImmutablePt2
import dev.bitspittle.kross2d.core.math.Pt2
import kotlin.math.PI

abstract class ImmutableCircle : ImmutableShape {
    abstract val radius: Float

    open val x: Float
        get() = center.x
    open val y: Float
        get() = center.y

    override val area: Float
        get() = (PI * radius * radius).toFloat()

    override fun toBoundingRect() = Rect(center.x - radius, center.y - radius, 2 * radius, 2 * radius)

    fun intersects(other: ImmutableCircle): Boolean {
        val radiiSum = other.radius + radius
        return (other.center - center).len2() < (radiiSum * radiiSum)
    }

    override fun equals(other: Any?): Boolean {
        if (other is ImmutableCircle) {
            return center == other.center && radius == other.radius
        }
        return false
    }

    override fun hashCode(): Int {
        return center.hashCode() + 31 * radius.hashCode()
    }

    override fun toString() = "Circle { ($x, $y), $radius }"
}

class Circle(center: ImmutablePt2, radius: Float) : ImmutableCircle() {
    constructor(): this(Pt2.Zero, 0f)
    constructor(radius: Float): this(Pt2.Zero, radius)
    constructor(x: Float, y: Float, radius: Float): this(Pt2(x, y), radius)
    constructor(x: Int, y: Int, radius: Int): this(Pt2(x, y), radius.toFloat())
    constructor(other: ImmutableCircle): this(other.center, other.radius)

    companion object {
        val Empty: ImmutableCircle = object : ImmutableCircle() {
            override val center: ImmutablePt2 = Pt2.Zero
            override val radius: Float = 0f
        }
    }

    override val center: Pt2 = Pt2(center)
    override var radius: Float = radius

    override var x: Float
        get() = super.x
        set(value) { center.x = value }

    override var y: Float
        get() = super.y
        set(value) { center.y = value }

    fun set(other: ImmutableCircle) {
        center.set(other.center)
        radius = other.radius
    }
}

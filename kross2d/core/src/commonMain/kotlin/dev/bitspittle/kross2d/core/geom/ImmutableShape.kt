package dev.bitspittle.kross2d.core.geom

import dev.bitspittle.kross2d.core.math.Pt2

interface Shape {
    val area: Float
    val center: Pt2
    fun toBoundingRect(): Rect
}

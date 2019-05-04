package bitspittle.kross2d.core.geom

import bitspittle.kross2d.core.math.ImmutablePt2

interface ImmutableShape {
    val area: Float
    val center: ImmutablePt2
    fun toBoundingRect(): ImmutableRect
}

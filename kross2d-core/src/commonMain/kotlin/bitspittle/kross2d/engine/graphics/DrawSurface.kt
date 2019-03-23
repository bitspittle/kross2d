package bitspittle.kross2d.engine.graphics

import bitspittle.kross2d.core.graphics.Color
import bitspittle.kross2d.core.math.ImmutablePt2
import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.core.math.Pt2

/**
 * Read-only properties of a [DrawSurface]
 */
interface ImmutableDrawSurface {
    val size: ImmutableVec2
}

/**
 * A surface area for rendering, which may be on or off-screen.
 */
interface DrawSurface : ImmutableDrawSurface {
    class DrawParams(
        val dest: ImmutablePt2 = Pt2.ZERO,
        val destSize: ImmutableVec2? = null,
        val src: ImmutablePt2 = Pt2.ZERO,
        val srcSize: ImmutableVec2? = null)

    fun clear(color: Color)
    fun draw(image: Image, params: DrawParams)
}
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
    class DrawParams(val dest: ImmutablePt2 = Pt2.ZERO, val destSize: ImmutableVec2? = null)

    fun clear(color: Color)

    /**
     * Draw the target image onto this surface.
     *
     * Using the passed in [DrawParams], you can transform (e.g. translate and scale) the whole
     * image before it is rendered on the surface. If you wish to only draw a section of the target
     * image, create a subsection of it using the subimage [Image] constructor.
     */
    fun draw(image: Image, params: DrawParams = DrawParams())
}
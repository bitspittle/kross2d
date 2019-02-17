package bitspittle.kross2d.engine.graphics

import bitspittle.kross2d.core.graphics.Color
import bitspittle.kross2d.core.math.ImmutableVec2

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
    fun clear(color: Color)
}
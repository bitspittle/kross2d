package bitspittle.kross2d.engine.graphics

import bitspittle.kross2d.core.math.ImmutableVec2

/**
 * A surface area for rendering, which may be on or off-screen.
 *
 * See also [DrawSurface.draw]
 */
expect class Image {
    val size: ImmutableVec2
}

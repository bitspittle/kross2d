package bitspittle.kross2d.engine.graphics

import bitspittle.kross2d.core.graphics.Colors
import bitspittle.kross2d.core.graphics.ImmutableColor
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
    class ImageParams(val dest: ImmutablePt2 = Pt2.ZERO, val destSize: ImmutableVec2? = null)
    class TextParams(
        val dest: ImmutablePt2 = Pt2.ZERO,
        val color: ImmutableColor = Colors.WHITE,

        /**
         * The space between lines in case the rendered text has newlines in it.
         */
        val spacing: Float = 0f)

    fun clear(color: ImmutableColor)

    /**
     * Draw the target image onto this surface.
     *
     * Using the passed in [ImageParams], you can transform (e.g. translate and scale) the whole
     * image before it is rendered. If you wish to only drawImage a section of the target image,
     * create a subsection of it using the subimage [Image] constructor.
     */
    fun drawImage(image: Image, params: ImageParams = ImageParams())

    /**
     * Determine the width needed to render the target text. Newlines are ignored.
     */
    fun measureText(font: Font, text: String): Float

    /**
     * Draw the specified text onto this surface, rendering using the supplied [Font]
     *
     * For convenience, newlines are supported. Use [TextParams.spacing] to configure the space
     * between lines.
     */
    fun drawText(font: Font, text: String, params: TextParams = TextParams())
}
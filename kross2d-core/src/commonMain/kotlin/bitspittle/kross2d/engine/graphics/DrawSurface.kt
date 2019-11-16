package bitspittle.kross2d.engine.graphics

import bitspittle.kross2d.core.graphics.Colors
import bitspittle.kross2d.core.graphics.ImmutableColor
import bitspittle.kross2d.core.math.ImmutablePt2
import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.core.math.Pt2
import bitspittle.kross2d.core.math.Vec2
import bitspittle.kross2d.engine.graphics.DrawSurface.TextParams.Anchor

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
        /**
         * The point which the text is drawn from. See also [anchor].
         */
        val pt: ImmutablePt2 = Pt2.ZERO,

        /**
         * The anchor point which influences the behavior of [pt].
         *
         * For example, an anchor of [Anchor.CENTER] will draw the text centered around [pt]
         * while [Anchor.RIGHT] will right-justify the text with [pt] at the right side.
         */
        val anchor: Anchor = Anchor.TOP_LEFT,
        val color: ImmutableColor = Colors.WHITE,

        /**
         * The space between lines in case the rendered text has newlines in it.
         */
        val spacing: Float = 0f) {

        enum class Anchor {
            TOP_LEFT, TOP, TOP_RIGHT,
            LEFT, CENTER, RIGHT,
            BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT
        }

        /**
         * Helper method for converting the current [pt] to the top-left point of the text area.
         *
         * [produceWidth] and [produceHeight] methods are passed in as lambdas and not an actual
         * values to avoid doing expensive calculations unnecessarily.
         */
        internal fun toTopLeft(produceWidth: () -> Float, produceHeight: () -> Float): ImmutablePt2 {
            return when (anchor) {
                Anchor.TOP_LEFT -> pt
                Anchor.TOP -> pt - Vec2(produceWidth() / 2f, 0f)
                Anchor.TOP_RIGHT -> pt - Vec2(produceWidth(), 0f)
                Anchor.LEFT -> pt - Vec2(0f, produceHeight() / 2f)
                Anchor.CENTER -> pt - Vec2(produceWidth() / 2f, produceHeight() / 2f)
                Anchor.RIGHT -> pt - Vec2(produceWidth(), produceHeight() / 2f)
                Anchor.BOTTOM_LEFT -> pt - Vec2(0f, produceHeight())
                Anchor.BOTTOM -> pt - Vec2(produceWidth() / 2f, produceHeight())
                Anchor.BOTTOM_RIGHT -> pt - Vec2(produceWidth(), produceHeight())
            }
        }
    }

    fun clear(color: ImmutableColor)

    fun drawLine(pt1: ImmutablePt2, pt2: ImmutablePt2, color: ImmutableColor)

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
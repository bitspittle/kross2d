package bitspittle.kross2d.engine.graphics

import bitspittle.kross2d.core.memory.Disposable

/**
 * A true-type font which can be used to render text.
 *
 * A font is locked at a particular size. If you need fonts at different sizes, construct new ones
 * using the constructor which takes a size value.
 *
 * See also: [DrawSurface.drawText]
 */
class Font internal constructor(internal val fontData: FontData): Disposable {
    companion object {
        const val DEFAULT_SIZE = 12f
    }

    /**
     * Construct an instance of this font at a different size.
     */
    constructor(other: Font, fontSize: Float): this(other.fontData.derive(fontSize))

    val size = fontData.size
}

internal expect class FontData {
    /**
     * Create a new instance of this class with a different font size
     */
    fun derive(fontSize: Float): FontData

    val size: Float
}

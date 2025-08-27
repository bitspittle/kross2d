package dev.bitspittle.kross2d.engine.graphics

import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.setParent

/**
 * A true-type font which can be used to render text.
 *
 * A font is locked at a particular size. If you need fonts at different sizes, construct new ones
 * using the constructor which takes a size value.
 *
 * See also: [DrawSurface.drawText]
 */
class Font internal constructor(internal val fontData: FontData): Disposable() {
    companion object {
        const val DEFAULT_SIZE = 12f
    }

    val size = fontData.size
    fun derive(fontSize: Float): Font {
        return Font(fontData.derive(fontSize)).setParent(this)
    }
}

internal expect class FontData {
    /**
     * Create a new instance of this class with a different font size
     */
    fun derive(fontSize: Float): FontData

    val size: Float
}

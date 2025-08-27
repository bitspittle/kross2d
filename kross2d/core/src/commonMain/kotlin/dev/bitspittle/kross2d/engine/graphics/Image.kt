package dev.bitspittle.kross2d.engine.graphics

import dev.bitspittle.kross2d.core.math.Pt2
import dev.bitspittle.kross2d.core.math.Vec2
import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.setParent

/**
 * A 2D image (or sub-portion of that image).
 *
 * See also [MutableDrawSurface.drawImage]
 */
class Image internal constructor(
    internal val data: ImageData,
    internal val pos: Pt2,
    private val sizeOverride: Vec2?) : Disposable() {

    /**
     * Construct an initial image, backed by image data
     */
    internal constructor(data: ImageData) : this(data, Pt2.Zero, null)

    val size: Vec2
        get() = sizeOverride ?: data.size

    fun subimage(pos: Pt2, size: Vec2): Image {
        return Image(data, pos, size).setParent(this)
    }
}

/**
 * Class that exposes platform-specific image implementation
 */
internal expect class ImageData {
    val size: Vec2
}

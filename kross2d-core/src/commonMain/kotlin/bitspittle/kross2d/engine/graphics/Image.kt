package bitspittle.kross2d.engine.graphics

import bitspittle.kross2d.core.math.ImmutablePt2
import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.core.math.Pt2
import bitspittle.kross2d.core.math.Vec2
import bitspittle.kross2d.core.memory.*

/**
 * A 2D image (or sub-portion of that image).
 *
 * See also [DrawSurface.drawImage]
 */
class Image internal constructor(
    internal val data: ImageData,
    internal val pos: ImmutablePt2,
    private val sizeOverride: ImmutableVec2?) : Disposable() {

    /**
     * Construct an initial image, backed by image data
     */
    internal constructor(data: ImageData) : this(data, Pt2.ZERO, null)

    val size: ImmutableVec2
        get() = sizeOverride ?: data.size

    fun subimage(pos: ImmutablePt2, size: ImmutableVec2): Image {
        return Image(data, pos, size).setParent(this)
    }
}

/**
 * Class that exposes platform-specific image implementation
 */
internal expect class ImageData {
    val size: ImmutableVec2
}

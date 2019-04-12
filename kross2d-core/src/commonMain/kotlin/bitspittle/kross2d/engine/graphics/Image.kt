package bitspittle.kross2d.engine.graphics

import bitspittle.kross2d.core.math.ImmutablePt2
import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.core.math.Pt2
import bitspittle.kross2d.core.math.Vec2
import bitspittle.kross2d.core.memory.Box
import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.Disposer

/**
 * A 2D image (or sub-portion of that image).
 *
 * See also [DrawSurface.drawImage]
 */
class Image internal constructor(
    internal val data: ImageData,
    internal val pos: ImmutablePt2,
    private val sizeOverride: ImmutableVec2?) : Disposable {

    /**
     * Construct an initial image, backed by image data
     */
    internal constructor(data: ImageData) : this(data, Pt2.ZERO, null)

    val size: ImmutableVec2
        get() = sizeOverride ?: data.size
}

fun Box<Image>.subimage(pos: ImmutablePt2, size: ImmutableVec2): Box<Image> {
    return this.deref { image -> Disposer.register(this, Image(image.data, pos, size)) }
}

/**
 * Class that exposes platform-specific image implementation
 */
internal expect class ImageData {
    val size: ImmutableVec2
}

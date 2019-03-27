package bitspittle.kross2d.engine.graphics

import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.core.math.Vec2
import org.w3c.dom.Image as JsImage

/**
 * A surface area for rendering, which may be on or off-screen.
 */
internal actual class ImageData(path: String) {
    val jsImage: JsImage = JsImage().apply { src = path }

    private var _size: ImmutableVec2? = null
    actual val size: ImmutableVec2
        get() {
            // JavaScript images load asynchronously, so cache their value only after they've
            // finished loading.
            if (_size == null && jsImage.width > 0) {
                _size = Vec2(jsImage.width, jsImage.height)
            }
            return _size ?: Vec2.ZERO
        }
}
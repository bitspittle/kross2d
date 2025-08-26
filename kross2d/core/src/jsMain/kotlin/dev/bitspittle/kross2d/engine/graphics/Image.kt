package dev.bitspittle.kross2d.engine.graphics

import dev.bitspittle.kross2d.core.math.Vec2
import org.w3c.dom.Image as JsImage

/**
 * A surface area for rendering, which may be on or off-screen.
 */
internal actual class ImageData(path: String) {
    private var _size: Vec2 = Vec2.Zero
    val jsImage: JsImage = JsImage().apply {
        onload = {
            _size = Vec2(jsImage.naturalWidth, jsImage.naturalHeight)
        }
        src = path
    }

    actual val size: Vec2 get() = _size
}
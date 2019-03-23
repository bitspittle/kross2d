package bitspittle.kross2d.engine.graphics

import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.core.math.Vec2
import org.w3c.dom.Image as JsImage

/**
 * A surface area for rendering, which may be on or off-screen.
 */
actual class Image(path: String) {
    val jsImage: JsImage = JsImage().apply { src = path }

    actual val size: ImmutableVec2
        get() = Vec2(jsImage.width, jsImage.height)
}
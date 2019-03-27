package bitspittle.kross2d.engine.assets

import bitspittle.kross2d.engine.graphics.Image
import bitspittle.kross2d.engine.graphics.ImageData

actual class AssetLoader actual constructor(private val root: String) {
    actual fun loadImage(relativePath: String): Image? {
        return Image(ImageData("$root/$relativePath"))
    }
}
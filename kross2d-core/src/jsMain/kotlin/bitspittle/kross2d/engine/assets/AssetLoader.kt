package bitspittle.kross2d.engine.assets

import bitspittle.kross2d.engine.graphics.Image

actual class AssetLoader actual constructor(private val root: String) {
    actual fun loadImage(relativePath: String): Image? {
        return Image("$root/$relativePath")
    }
}
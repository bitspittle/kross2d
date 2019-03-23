package bitspittle.kross2d.engine.assets

import bitspittle.kross2d.engine.graphics.Image

actual class AssetLoader actual constructor(root: String) {
    private val resourceLoader = ResourceLoader(root)
    actual fun loadImage(relativePath: String): Image? {
        return resourceLoader.load(relativePath)?.let { Image(it) }
    }
}
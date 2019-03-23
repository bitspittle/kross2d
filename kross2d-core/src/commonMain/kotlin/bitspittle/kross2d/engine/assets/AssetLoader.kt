package bitspittle.kross2d.engine.assets

import bitspittle.kross2d.engine.graphics.Image

expect class AssetLoader(root: String) {
    fun loadImage(relativePath: String): Image?
}

package bitspittle.kross2d.engine.assets

import bitspittle.kross2d.engine.graphics.Image
import bitspittle.kross2d.engine.graphics.ImageData

actual class AssetLoaderBackend actual constructor(private val root: String) {
    actual fun loadImageInto(asset: Asset<Image>) {
        val data = ImageData("$root/${asset.path}")
        data.jsImage.onload = { asset.setValue(Image(data)) }
        data.jsImage.onerror = { _, _, _, _, _ -> asset.notifyFailure() }
    }
}
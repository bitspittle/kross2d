package bitspittle.kross2d.engine.assets

import bitspittle.kross2d.engine.graphics.Image
import bitspittle.kross2d.engine.graphics.ImageData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.nio.channels.AsynchronousFileChannel

actual class AssetLoaderBackend actual constructor(root: String) {
    private val resourceLoader = ResourceLoader(root)
    actual fun loadImageInto(asset: Asset<Image>) {
        GlobalScope.launch(Dispatchers.IO) {
            asset.setValue(resourceLoader.load(asset.path)?.let { Image(ImageData(it)) })
        }
    }
}
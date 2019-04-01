package bitspittle.kross2d.engine.assets

import bitspittle.kross2d.engine.audio.Sound
import bitspittle.kross2d.engine.graphics.Image
import bitspittle.kross2d.engine.graphics.ImageData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

actual class AssetLoaderBackend actual constructor(root: String) {
    private val resourceLoader = ResourceLoader(root)
    actual fun loadImageInto(asset: Asset<Image>) {
        GlobalScope.launch(Dispatchers.IO) {
            asset.setValue(resourceLoader.load(asset.path)?.let { Image(ImageData(it)) })
        }
    }

    actual fun loadSoundInto(asset: Asset<Sound>) {
        GlobalScope.launch(Dispatchers.IO) {
            resourceLoader.stream(asset.path)
                ?.let { asset.setValue(Sound.tryCreate(it)) }
                ?: asset.notifyFailure()
        }
    }
}
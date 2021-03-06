package bitspittle.kross2d.engine.assets

import bitspittle.kross2d.engine.audio.Music
import bitspittle.kross2d.engine.audio.Sound
import bitspittle.kross2d.engine.graphics.Font
import bitspittle.kross2d.engine.graphics.FontData
import bitspittle.kross2d.engine.graphics.Image
import bitspittle.kross2d.engine.graphics.ImageData
import kotlinx.coroutines.*

actual class AssetLoaderBackend actual constructor(root: String) {
    private val resourceLoader = ResourceLoader(root)

    actual fun loadFontInto(asset: Asset<Font>) {
        GlobalScope.launch(Dispatchers.IO) {
            asset.setData(
                resourceLoader.stream(asset.path)
                    ?.let { FontData.tryCreate(it) }
                    ?.let { fontData -> Font(fontData) })
        }
    }

    actual fun loadImageInto(asset: Asset<Image>) {
        GlobalScope.launch(Dispatchers.IO) {
            asset.setData(resourceLoader.load(asset.path)?.let { Image(ImageData(it)) })
        }
    }

    actual fun loadSoundInto(asset: Asset<Sound>) {
        GlobalScope.launch(Dispatchers.IO) {
            asset.setData(resourceLoader.stream(asset.path)?.let { Sound.tryCreate(it) })
        }
    }

    actual fun loadMusicInto(asset: Asset<Music>) {
        GlobalScope.launch(Dispatchers.IO) {
            asset.setData(resourceLoader.url(asset.path)?.let { Music.tryCreate(it) })
        }
    }
}
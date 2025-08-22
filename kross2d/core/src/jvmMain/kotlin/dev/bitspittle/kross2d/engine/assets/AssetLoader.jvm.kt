package dev.bitspittle.kross2d.engine.assets

import dev.bitspittle.kross2d.engine.audio.Music
import dev.bitspittle.kross2d.engine.audio.Sound
import dev.bitspittle.kross2d.engine.graphics.Font
import dev.bitspittle.kross2d.engine.graphics.FontData
import dev.bitspittle.kross2d.engine.graphics.Image
import dev.bitspittle.kross2d.engine.graphics.ImageData
import kotlinx.coroutines.*

actual class AssetLoaderBackend actual constructor(root: String) {
    private val resourceLoader = ResourceLoader(root)

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    actual fun loadFontInto(asset: Asset<Font>) {
        scope.launch(Dispatchers.IO) {
            asset.setData(
                resourceLoader.stream(asset.path)
                    ?.let { FontData.tryCreate(it) }
                    ?.let { fontData -> Font(fontData) })
        }
    }

    actual fun loadImageInto(asset: Asset<Image>) {
        scope.launch(Dispatchers.IO) {
            asset.setData(resourceLoader.load(asset.path)?.let { Image(ImageData(it)) })
        }
    }

    actual fun loadSoundInto(asset: Asset<Sound>) {
        scope.launch(Dispatchers.IO) {
            asset.setData(resourceLoader.stream(asset.path)?.let { Sound.tryCreate(it) })
        }
    }

    actual fun loadMusicInto(asset: Asset<Music>) {
        scope.launch(Dispatchers.IO) {
            asset.setData(resourceLoader.url(asset.path)?.let { Music.tryCreate(it) })
        }
    }
}
package bitspittle.kross2d.engine.assets

import bitspittle.kross2d.engine.audio.Sound
import bitspittle.kross2d.engine.graphics.Font
import bitspittle.kross2d.engine.graphics.FontData
import bitspittle.kross2d.engine.graphics.Image
import bitspittle.kross2d.engine.graphics.ImageData
import kotlin.browser.document

actual class AssetLoaderBackend actual constructor(private val root: String) {

    actual fun loadFontInto(asset: Asset<Font>) {
        val data = FontData("$root/${asset.path}")
        data.jsFont.loaded
            .then {
                document.asDynamic().fonts.add(it) // At this time, document.fonts is still experimental
                asset.setValue(Font(data))
            }
            .catch { asset.notifyFailure() }
    }

    actual fun loadImageInto(asset: Asset<Image>) {
        val data = ImageData("$root/${asset.path}")
        data.jsImage.onload = { asset.setValue(Image(data)) }
        data.jsImage.onerror = { _, _, _, _, _ -> asset.notifyFailure() }
    }

    actual fun loadSoundInto(asset: Asset<Sound>) {
        val sound = Sound("$root/${asset.path}")
        sound.jsAudio.onloadeddata = { asset.setValue(sound) }
        sound.jsAudio.onerror = { _, _, _, _, _ -> asset.notifyFailure() }
    }
}
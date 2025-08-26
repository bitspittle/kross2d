package dev.bitspittle.kross2d.engine.assets

import dev.bitspittle.kross2d.engine.audio.AudioBuffer
import dev.bitspittle.kross2d.engine.audio.Music
import dev.bitspittle.kross2d.engine.audio.Sound
import dev.bitspittle.kross2d.engine.graphics.Font
import dev.bitspittle.kross2d.engine.graphics.FontData
import dev.bitspittle.kross2d.engine.graphics.Image
import dev.bitspittle.kross2d.engine.graphics.ImageData
import kotlinx.browser.document

actual class AssetLoaderBackend actual constructor(private val root: String) {

    actual fun loadFontInto(asset: Asset<Font>) {
        val data = FontData("$root/${asset.path}")
        data.jsFont.loaded
            .then {
                document.asDynamic().fonts.add(it) // At this time, document.fonts is still experimental
                asset.setData(Font(data))
            }
            .catch { asset.notifyFailure() }
    }

    actual fun loadImageInto(asset: Asset<Image>) {
        val data = ImageData("$root/${asset.path}")
        data.jsImage.onload = { asset.setData(Image(data)) }
        data.jsImage.onerror = { _, _, _, _, _ -> asset.notifyFailure() }
    }

    actual fun loadSoundInto(asset: Asset<Sound>) {
        val buffer = AudioBuffer.InMemory("$root/${asset.path}")
        buffer.jsAudio.onloadeddata = { asset.setData(Sound(buffer)) }
        buffer.jsAudio.onerror = { _, _, _, _, _ -> asset.notifyFailure() }
    }

    actual fun loadMusicInto(asset: Asset<Music>) {
        val buffer = AudioBuffer.Streaming("$root/${asset.path}")
        buffer.jsAudio.onloadeddata = { asset.setData(Music(buffer)) }
        buffer.jsAudio.onerror = { _, _, _, _, _ -> asset.notifyFailure() }
    }
}
package dev.bitspittle.kross2d.engine.graphics

import kotlin.js.Promise

external class FontFace(family: String, src: String) {
    fun load(): Promise<FontFace>
    val loaded: Promise<FontFace>
}

internal actual class FontData(val name: String, fontFace: FontFace, actual val size: Float) {
    constructor(fontName: String, path: String) : this(
        fontName,
        FontFace(family = fontName, src = "url($path)").also { it.load() },
        Font.DEFAULT_SIZE)

    constructor(path: String) : this(path.substringAfterLast('/').substringBefore('.'), path)

    val jsFont: FontFace = fontFace.also { it.load() }

    actual fun derive(fontSize: Float): FontData {
        return FontData(name, jsFont, fontSize)
    }
}
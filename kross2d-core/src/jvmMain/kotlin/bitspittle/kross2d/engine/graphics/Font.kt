package bitspittle.kross2d.engine.graphics

import java.awt.Font.TRUETYPE_FONT
import java.awt.FontFormatException
import java.io.InputStream
import java.awt.Font as AwtFont

internal actual class FontData private constructor(val awtFont: AwtFont) {
    companion object {
        fun tryCreate(stream: InputStream): FontData? {
            return try {
                FontData(AwtFont.createFont(TRUETYPE_FONT, stream).deriveFont(Font.DEFAULT_SIZE))
            }
            catch (_: FontFormatException) {
                null
            }
        }
    }

    actual fun derive(fontSize: Float): FontData = FontData(awtFont.deriveFont(fontSize))

    actual val size
        get() = awtFont.size2D
}
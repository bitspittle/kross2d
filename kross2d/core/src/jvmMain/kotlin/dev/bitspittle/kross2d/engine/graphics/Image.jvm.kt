package dev.bitspittle.kross2d.engine.graphics

import dev.bitspittle.kross2d.core.math.Vec2
import javax.imageio.ImageIO
import java.awt.Image as AwtImage

/**
 * A surface area for rendering, which may be on or off-screen.
 */
internal actual class ImageData(bytes: ByteArray) {
    /**
     * For the JVM implementation, back this image using a Swing image.
     */
    val awtImage: AwtImage = ImageIO.read(bytes.inputStream())

    actual val size = Vec2(awtImage.getWidth(null), awtImage.getHeight(null))
}
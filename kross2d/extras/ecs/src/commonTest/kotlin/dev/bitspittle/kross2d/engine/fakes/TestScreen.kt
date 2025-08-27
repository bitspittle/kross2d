package dev.bitspittle.kross2d.engine.fakes

import dev.bitspittle.kross2d.core.graphics.Color
import dev.bitspittle.kross2d.core.graphics.Colors
import dev.bitspittle.kross2d.core.math.Pt2
import dev.bitspittle.kross2d.core.math.Vec2
import dev.bitspittle.kross2d.engine.graphics.*

class TestScreen : MutableScreen {
    override val size: Vec2
        get() = TODO("not implemented")

    var clearColor = Colors.Black
        private set

    override fun clear(color: Color) {
        clearColor = color
    }

    override fun drawLine(pt1: Pt2, pt2: Pt2, color: Color) {
        TODO("Not yet implemented")
    }

    override fun pushTransform(transform: Screen.Transform) {
        TODO("not implemented")
    }

    override fun popTransform() {
        TODO("not implemented")
    }

    override fun drawImage(image: Image, params: DrawSurface.ImageParams) {
        TODO("not implemented")
    }

    override fun measureText(font: Font, text: String): Float {
        TODO("not implemented")
    }

    override fun drawText(font: Font, text: String, params: DrawSurface.TextParams) {
        TODO("not implemented")
    }
}
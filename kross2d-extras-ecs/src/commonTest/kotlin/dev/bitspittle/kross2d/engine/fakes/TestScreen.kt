package dev.bitspittle.kross2d.engine.fakes

import dev.bitspittle.kross2d.core.graphics.Colors
import dev.bitspittle.kross2d.core.graphics.ImmutableColor
import dev.bitspittle.kross2d.core.math.ImmutablePt2
import dev.bitspittle.kross2d.core.math.ImmutableVec2
import dev.bitspittle.kross2d.engine.graphics.DrawSurface
import dev.bitspittle.kross2d.engine.graphics.Font
import dev.bitspittle.kross2d.engine.graphics.Image
import dev.bitspittle.kross2d.engine.graphics.Screen

class TestScreen : Screen {
    override val size: ImmutableVec2
        get() = TODO("not implemented")

    var clearColor: ImmutableColor = Colors.BLACK
        private set

    override fun clear(color: ImmutableColor) {
        clearColor = color
    }

    override fun drawLine(pt1: ImmutablePt2, pt2: ImmutablePt2, color: ImmutableColor) {
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
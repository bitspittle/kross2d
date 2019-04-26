package bitspittle.kross2d.engine.fakes

import bitspittle.kross2d.core.graphics.Colors
import bitspittle.kross2d.core.graphics.ImmutableColor
import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.engine.graphics.DrawSurface
import bitspittle.kross2d.engine.graphics.Font
import bitspittle.kross2d.engine.graphics.Image
import bitspittle.kross2d.engine.graphics.Screen

class TestScreen : Screen {
    override val size: ImmutableVec2
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    var clearColor: ImmutableColor = Colors.BLACK
        private set

    override fun clear(color: ImmutableColor) {
        clearColor = color
    }

    override fun pushTransform(transform: Screen.Transform) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun popTransform() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun drawImage(image: Image, params: DrawSurface.ImageParams) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun measureText(font: Font, text: String): Float {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun drawText(font: Font, text: String, params: DrawSurface.TextParams) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
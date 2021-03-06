package bitspittle.kross2d.engine.input

import bitspittle.kross2d.core.math.ImmutablePt2
import bitspittle.kross2d.core.math.Pt2

enum class Button {
    LEFT,
    MIDDLE,
    RIGHT,
}

/**
 * An interface the location and button states of the mouse
 */
interface Mouse {
    val pos: ImmutablePt2
    fun isJustPressed(button: Button): Boolean
    fun isDown(button: Button): Boolean
    fun isJustReleased(button: Button): Boolean
    fun isUp(button: Button): Boolean
}

internal class DefaultMouse : Mouse {
    override val pos = Pt2()
    private val buttonsPrev = BooleanArray(Button.values().size) { false }
    private val buttonsCurr = BooleanArray(Button.values().size) { false }

    fun handleButton(button: Button, isDown: Boolean) {
        buttonsCurr[button.ordinal] = isDown
    }

    fun step() {
        buttonsCurr.copyInto(buttonsPrev)
    }

    override fun isJustPressed(button: Button) = !buttonsPrev[button.ordinal] && buttonsCurr[button.ordinal]
    override fun isDown(button: Button) = buttonsCurr[button.ordinal]
    override fun isJustReleased(button: Button) = buttonsPrev[button.ordinal] && !buttonsCurr[button.ordinal]
    override fun isUp(button: Button) = !buttonsCurr[button.ordinal]
}
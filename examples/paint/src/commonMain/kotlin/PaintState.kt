import dev.bitspittle.kross2d.core.graphics.Colors
import dev.bitspittle.kross2d.core.math.Pt2
import dev.bitspittle.kross2d.engine.GameState
import dev.bitspittle.kross2d.engine.context.DrawContext
import dev.bitspittle.kross2d.engine.context.UpdateContext
import dev.bitspittle.kross2d.engine.graphics.MutableDrawSurface
import dev.bitspittle.kross2d.engine.input.Button
import dev.bitspittle.kross2d.engine.input.Key
import dev.bitspittle.kross2d.engine.input.Mouse

private val CLEAR_COLOR = Colors.LightGray

/**
 * Images: A simple "game" which renders lines drawn by holding the mouse down.
 *
 * This demo app is intentionally minimal, focusing on basic mouse manipulation.
 *
 * Demonstrated:
 * - Using [Mouse]
 * - Rendering lines using [MutableDrawSurface.drawLine]
 */
class PaintState : GameState {
    private val paths = mutableListOf<MutableList<Pt2>>()
    private var currPath: MutableList<Pt2>? = null

    override fun update(ctx: UpdateContext) {
        if (ctx.keyboard.isJustPressed(Key.ESC)) {
            ctx.app.quit()
        }
        else if (ctx.keyboard.isJustPressed(Key.BACKSPACE)) {
            paths.clear()
        }

        if (ctx.mouse.isDown(Button.LEFT)) {
            if (currPath == null) {
                paths.add(mutableListOf())
                currPath = paths.last()
            }

            val redundantPoint = currPath!!.lastOrNull()?.equals(ctx.mouse.pos) ?: false
            if (!redundantPoint) {
                currPath!!.add(ctx.mouse.pos.toPt2())
            }
        }
        else {
            currPath = null
        }
    }

    override fun draw(ctx: DrawContext) {
        ctx.screen.clear(CLEAR_COLOR)

        paths.asSequence()
            .filter { line -> line.size >= 2 }
            .forEach { points ->
                for (i in 1 until points.size) {
                    ctx.screen.drawLine(points[i - 1], points[i], Colors.Black)
                }
            }
    }
}

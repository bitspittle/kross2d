import bitspittle.kross2d.core.geom.Rect
import bitspittle.kross2d.core.graphics.Colors
import bitspittle.kross2d.core.memory.disposable
import bitspittle.kross2d.core.memory.setParent
import bitspittle.kross2d.engine.GameState
import bitspittle.kross2d.engine.app.ApplicationFacade
import bitspittle.kross2d.engine.assets.Asset
import bitspittle.kross2d.engine.context.DrawContext
import bitspittle.kross2d.engine.context.EnterContext
import bitspittle.kross2d.engine.context.UpdateContext
import bitspittle.kross2d.engine.graphics.DrawSurface.TextParams
import bitspittle.kross2d.engine.graphics.Font
import bitspittle.kross2d.engine.input.Key


/**
 * Many States: A demo which isolates moving between multiple [GameState]s
 *
 * The following state transitions are allowed:
 *
 * - Initial -> Middle (by pushing)
 * - Middle <- Initial (by popping)
 * - Middle -> End (by changing, thereby dropping Middle)
 * - End -> Initial (by popping)
 *
 * Demonstrated:
 * - Using [ApplicationFacade.pushState], [ApplicationFacade.changeState], and
 *   [ApplicationFacade.popState]
 * - Understanding lifetimes (i.e. when assets are released)
 */
class InitialState : GameState {
    private lateinit var font: Asset<Font>
    private val fontDisposedListener = disposable {
        println("Font was disposed")
    }

    override fun enter(ctx: EnterContext) {
        // Note: We load the font using `this` as its lifetime, since we don't actually kill the
        // initial state until we finally quit
        // If we didn't do this, the font would be tied to the active state and would get released
        // as soon as we pushed to a new state.
        font = ctx.assetLoader.loadFont("square.ttf", ctx.lifetimes.app)

        // Remember, enter can get called multiple times. Subsequent calls to reparent are
        // no-ops.
        fontDisposedListener.setParent(font)

        disposable(ctx.lifetimes.currState) {
            println("InitialState is no longer active")
        }
    }

    override fun update(ctx: UpdateContext) {
        if (ctx.keyboard.isJustPressed(Key.ESC)) {
            ctx.app.quit()
        }
        else if (ctx.keyboard.isJustPressed(Key.RIGHT)) {
            ctx.app.pushState(MiddleState(font))
        }
    }

    override fun draw(ctx: DrawContext) {
        ctx.screen.clear(Colors.BLACK)
        font.value?.let { font ->
            ctx.screen.drawText(
                font, """
                INITIAL STATE

                press RIGHT ARROW to go forward
                press ESC to quit
                """.trimIndent(),
                TextParams(Rect(ctx.screen.size).center, TextParams.Anchor.BOTTOM)
            )
        }
    }
}

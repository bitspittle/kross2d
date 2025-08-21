import dev.bitspittle.kross2d.core.geom.Rect
import dev.bitspittle.kross2d.core.graphics.Colors
import dev.bitspittle.kross2d.core.memory.disposable
import dev.bitspittle.kross2d.core.memory.setParent
import dev.bitspittle.kross2d.engine.GameState
import dev.bitspittle.kross2d.engine.app.ApplicationFacade
import dev.bitspittle.kross2d.engine.assets.Asset
import dev.bitspittle.kross2d.engine.context.DrawContext
import dev.bitspittle.kross2d.engine.context.EnterContext
import dev.bitspittle.kross2d.engine.context.UpdateContext
import dev.bitspittle.kross2d.engine.graphics.DrawSurface.TextParams
import dev.bitspittle.kross2d.engine.graphics.Font
import dev.bitspittle.kross2d.engine.input.Key


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
 * - Understanding scopes (i.e. when assets are released)
 */
class InitialState : GameState {
    private lateinit var font: Asset<Font>
    private val fontDisposedListener = disposable {
        println("Font was disposed")
    }

    override fun enter(ctx: EnterContext) {
        // The font will live across all states so register it to the global `app` scope
        font = ctx.assetLoader.loadFont("square.ttf", ctx.scopes.app)

        // Remember, enter can get called multiple times. Subsequent calls to reparent are
        // no-ops.
        fontDisposedListener.setParent(font)

        disposable(ctx.scopes.currState) {
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
        font.data?.let { font ->
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

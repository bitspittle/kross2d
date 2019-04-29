import bitspittle.kross2d.core.geom.Rect
import bitspittle.kross2d.core.graphics.Colors
import bitspittle.kross2d.core.memory.disposable
import bitspittle.kross2d.engine.GameState
import bitspittle.kross2d.engine.assets.Asset
import bitspittle.kross2d.engine.context.DrawContext
import bitspittle.kross2d.engine.context.EnterContext
import bitspittle.kross2d.engine.context.UpdateContext
import bitspittle.kross2d.engine.graphics.DrawSurface.TextParams
import bitspittle.kross2d.engine.graphics.Font
import bitspittle.kross2d.engine.input.Key

class EndState(private val font: Asset<Font>) : GameState {
    override fun enter(ctx: EnterContext) {
        disposable(ctx.scopes.currState) {
            println("EndState is no longer active")
        }
    }

    override fun update(ctx: UpdateContext) {
        if (ctx.keyboard.isJustPressed(Key.ESC)) {
            ctx.app.popState()
        }
    }

    override fun draw(ctx: DrawContext) {
        ctx.screen.clear(Colors.BLACK)
        font.data?.let { font ->
            ctx.screen.drawText(
                font, """
                END STATE

                press ESC to return to INITIAL STATE
                """.trimIndent(),
                TextParams(Rect(ctx.screen.size).center, TextParams.Anchor.BOTTOM)
            )
        }
    }
}

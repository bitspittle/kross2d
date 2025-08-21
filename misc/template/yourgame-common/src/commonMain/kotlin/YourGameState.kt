import dev.bitspittle.kross2d.core.graphics.Colors
import dev.bitspittle.kross2d.engine.GameState
import dev.bitspittle.kross2d.engine.context.DrawContext
import dev.bitspittle.kross2d.engine.context.InitContext
import dev.bitspittle.kross2d.engine.context.UpdateContext
import dev.bitspittle.kross2d.engine.input.Key

class YourGameState : GameState {
    override fun init(ctx: InitContext) {
        // TODO: Start loading assets here
    }

    override fun update(ctx: UpdateContext) {
        if (ctx.keyboard.isJustPressed(Key.ESC)) {
            ctx.app.quit()
        }
    }

    override fun draw(ctx: DrawContext) {
        ctx.screen.clear(Colors.BLACK)
    }
}

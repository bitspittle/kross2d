import bitspittle.kross2d.core.graphics.Colors
import bitspittle.kross2d.engine.GameState
import bitspittle.kross2d.engine.context.DrawContext
import bitspittle.kross2d.engine.context.InitContext
import bitspittle.kross2d.engine.context.UpdateContext
import bitspittle.kross2d.engine.input.Key

class YourGameState : GameState {
    override fun init(ctx: InitContext) {
        // TODO: Start loading assets here
    }

    override fun update(ctx: UpdateContext) {
        if (ctx.keyboard.isDown(Key.ESC)) {
            ctx.app.quit()
        }
    }

    override fun draw(ctx: DrawContext) {
        ctx.screen.clear(Colors.BLACK)
    }
}

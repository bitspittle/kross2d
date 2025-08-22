import dev.bitspittle.kross2d.core.graphics.Color
import dev.bitspittle.kross2d.core.time.Duration
import dev.bitspittle.kross2d.core.time.ImmutableDuration
import dev.bitspittle.kross2d.engine.GameState
import dev.bitspittle.kross2d.engine.context.DrawContext
import dev.bitspittle.kross2d.engine.context.UpdateContext
import dev.bitspittle.kross2d.engine.input.Key
import dev.bitspittle.kross2d.engine.input.Keyboard
import dev.bitspittle.kross2d.engine.time.Timer
import kotlin.math.sin

/**
 * Hello World: A simple "game" which just shows a solid color that changes over time
 *
 * This demo app is intentionally minimal, to highlight the basics of what it takes to set up your
 * game using *kross2d*.
 *
 * Game logic will live in a common area, and a very thin, platform-specific runner will include it
 * as a library and start it up.
 *
 * Demonstrated:
 * - Basic [GameState] with [update] and [draw] calls
 * - [Keyboard] handling
 * - Using [Timer] to keep track of elapsed time
 * - Updating the screen via [DrawContext.screen]
 */
class HelloWorldState : GameState {
    private val clearColor = Color(0, 0, 0)
    private val elapsed = Duration.zero()

    private fun cycleValue(max: Int, t: ImmutableDuration): Int {
        // Convert time to 0 -> 1 -> 0 cycle, then multiply for 0 -> max -> 0
        return (((sin(t.secs) + 1.0) / 2.0) * max).toInt()
    }

    override fun update(ctx: UpdateContext) {
        if (ctx.keyboard.isJustPressed(Key.ESC)) {
            ctx.app.quit()
        }

        elapsed += ctx.timer.lastFrame

        // Update color, cycling its r, g, b parts at different rates so you don't just get a
        // boring "black -> grey -> white and back" effect
        clearColor.r = cycleValue(255, elapsed)
        clearColor.g = cycleValue(255, elapsed * 2.0)
        clearColor.b = cycleValue(255, elapsed * 3.0)
    }

    override fun draw(ctx: DrawContext) {
        ctx.screen.clear(clearColor)
    }
}

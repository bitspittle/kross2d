import bitspittle.kross2d.core.graphics.Color
import bitspittle.kross2d.engine.GameState
import bitspittle.kross2d.engine.audio.Sound
import bitspittle.kross2d.engine.context.DrawContext
import bitspittle.kross2d.engine.context.InitContext
import bitspittle.kross2d.engine.context.UpdateContext
import bitspittle.kross2d.engine.input.Key

private val CLEAR_COLOR = Color(0, 0, 0)

/**
 * Images: A simple "game" which plays sounds.
 *
 * This demo app is intentionally minimal, focusing on basic sound / music asset loading.
 *
 * Demonstrated:
 * - TODO
 */
class SoundsState : GameState {
    private val sounds = arrayOfNulls<Sound?>(10)
    private var globallyPaused = false

    override fun init(ctx: InitContext) {
        listOf(
            "boing.wav",
            "boxing_bell.wav",
            "chainsaw.wav",
            "creak.wav",
            "doorbell.wav",
            "honk.wav",
            "movie_projector.wav",
            "ricochet.wav",
            "slide_whistle.wav",
            "thunk.wav")
            .forEachIndexed { i, filename -> ctx.assetLoader.loadSound(filename).onLoaded += { sounds[i] = it }
        }
    }

    override fun update(ctx: UpdateContext) {
        if (ctx.keyboard.isJustPressed(Key.ESC)) {
            ctx.app.quit()
        }

        if (ctx.keyboard.isJustPressed(Key.SPACE)) {
            globallyPaused = !globallyPaused
            ctx.assetLoader.allSounds.forEach { if (globallyPaused) it.pause() else it.resume() }
        }

        if (!globallyPaused) {
            (Key.NUM_0.ordinal..Key.NUM_9.ordinal).forEachIndexed { i, keyOrdinal ->
                val key = Key.values()[keyOrdinal]
                if (ctx.keyboard.isJustPressed(key)) {
                    sounds.getOrNull(i)?.play()
                }
            }
        }
    }

    override fun draw(ctx: DrawContext) {
        ctx.screen.clear(CLEAR_COLOR)
    }
}

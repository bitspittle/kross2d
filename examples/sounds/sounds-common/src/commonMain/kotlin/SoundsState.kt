import bitspittle.kross2d.core.geom.Rect
import bitspittle.kross2d.core.geom.centerIn
import bitspittle.kross2d.core.graphics.Color
import bitspittle.kross2d.core.math.Pt2
import bitspittle.kross2d.core.math.Vec2
import bitspittle.kross2d.engine.GameState
import bitspittle.kross2d.engine.audio.Sound
import bitspittle.kross2d.engine.context.DrawContext
import bitspittle.kross2d.engine.context.InitContext
import bitspittle.kross2d.engine.context.UpdateContext
import bitspittle.kross2d.engine.graphics.DrawSurface.TextParams
import bitspittle.kross2d.engine.graphics.Font
import bitspittle.kross2d.engine.input.Key

private val CLEAR_COLOR = Color(0, 0, 0)

/**
 * Images: A simple "game" which plays sounds.
 *
 * This demo app is intentionally minimal, focusing on basic sound / music asset loading.
 *
 * Demonstrated:
 * - [Sound] loading, playing, and pausing
 * - [Font] rendering / multiple sizes
 */
class SoundsState : GameState {
    companion object {
        val STR_PAUSED = "PAUSED"
        val STR_MESSAGE =
            """
                press 0-9 to play sounds
                press SPACE to toggle pause
                press ESC to quit
            """.trimIndent()

    }

    private val sounds = arrayOfNulls<Sound?>(10)
    private var font: Font? = null
    private var fontLarge: Font? = null
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
        ctx.assetLoader.loadFont("square.ttf").onLoaded += { font = it; fontLarge = Font(font!!, 24f) }
    }

    override fun update(ctx: UpdateContext) {
        if (ctx.keyboard.isJustPressed(Key.ESC)) {
            ctx.app.quit()
        }

        if (ctx.keyboard.isJustPressed(Key.SPACE)) {
            globallyPaused = !globallyPaused
            sounds.filterNotNull().forEach { if (globallyPaused) it.pause() else it.resume() }
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

        if (globallyPaused) {
            fontLarge?.let { font ->
                // Pretty wasteful to recalculate every frame, but this is just a sample...
                // In your own code, consider caching this.
                // TODO: Support centering text in drawText?
                val area = Vec2(ctx.screen.measureText(font, STR_PAUSED), font.size)
                val pos = area.centerIn(Rect(ctx.screen.size))

                ctx.screen.drawText(font, STR_PAUSED, TextParams(dest = pos))
            }
        }
        else {
            font?.let { font ->
                ctx.screen.drawText(
                    font,
                    STR_MESSAGE,
                    TextParams(dest = Pt2(10, 10), spacing = font.size * 0.5f)
                )
            }
        }
    }
}

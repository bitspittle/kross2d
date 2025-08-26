import dev.bitspittle.kross2d.core.geom.Rect
import dev.bitspittle.kross2d.core.graphics.Colors
import dev.bitspittle.kross2d.core.math.Pt2
import dev.bitspittle.kross2d.engine.GameState
import dev.bitspittle.kross2d.engine.assets.Asset
import dev.bitspittle.kross2d.engine.audio.Music
import dev.bitspittle.kross2d.engine.audio.Sound
import dev.bitspittle.kross2d.engine.context.DrawContext
import dev.bitspittle.kross2d.engine.context.EnterContext
import dev.bitspittle.kross2d.engine.context.UpdateContext
import dev.bitspittle.kross2d.engine.graphics.DrawSurface.TextParams
import dev.bitspittle.kross2d.engine.graphics.DrawSurface.TextParams.Anchor
import dev.bitspittle.kross2d.engine.graphics.Font
import dev.bitspittle.kross2d.engine.input.Key

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
    object Strings {
        val PAUSED = "PAUSED"
        val HELP =
            """
                press 0-9 to play sounds
                press SPACE to toggle pause
                press ESC to quit
            """.trimIndent()
    }

    private lateinit var sounds: List<Asset<Sound>>
    private lateinit var music: Asset<Music>
    private var font: Font? = null
    private var fontLarge: Font? = null
    private var globallyPaused = false

    override fun enter(ctx: EnterContext) {
        sounds = listOf(
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
            .map { filename -> ctx.assetLoader.loadSound(filename) }
        ctx.assetLoader.loadFont("square.ttf").onLoaded += { font = it; fontLarge = it.derive(24f) }
        music = ctx.assetLoader.loadMusic("battle.ogg")
        music.onLoaded += { it.play() }
    }

    override fun update(ctx: UpdateContext) {
        if (ctx.keyboard.isJustPressed(Key.ESC)) {
            ctx.app.quit()
        }

        if (ctx.keyboard.isJustPressed(Key.SPACE)) {
            globallyPaused = !globallyPaused
            sounds
                .mapNotNull { it.data }
                .forEach { if (globallyPaused) it.pause() else it.resume() }

            music.data?.let { if (globallyPaused) it.pause() else it.resume() }
        }

        if (!globallyPaused) {
            (Key.NUM_0.ordinal..Key.NUM_9.ordinal).forEachIndexed { i, keyOrdinal ->
                val key = Key.values()[keyOrdinal]
                if (ctx.keyboard.isJustPressed(key)) {
                    sounds[i].data?.play()
                }
            }
        }
    }

    override fun draw(ctx: DrawContext) {
        ctx.screen.clear(Colors.Black)

        if (globallyPaused) {
            fontLarge?.let { font ->
                ctx.screen.drawText(font, Strings.PAUSED,
                    TextParams(pt = Rect(ctx.screen.size).center, anchor = Anchor.CENTER))
            }
        }
        else {
            font?.let { font ->
                ctx.screen.drawText(
                    font,
                    Strings.HELP,
                    TextParams(pt = Pt2(10, 10), spacing = font.size * 0.5f)
                )
            }
        }
    }
}

import bitspittle.kross2d.core.geom.Rect
import bitspittle.kross2d.core.geom.centerIn
import bitspittle.kross2d.core.graphics.Color
import bitspittle.kross2d.core.math.Pt2
import bitspittle.kross2d.core.math.Vec2
import bitspittle.kross2d.core.math.clamp
import bitspittle.kross2d.core.time.Duration
import bitspittle.kross2d.engine.GameState
import bitspittle.kross2d.engine.assets.Asset
import bitspittle.kross2d.engine.assets.AssetLoader
import bitspittle.kross2d.engine.context.DrawContext
import bitspittle.kross2d.engine.context.EnterContext
import bitspittle.kross2d.engine.context.UpdateContext
import bitspittle.kross2d.engine.graphics.DrawSurface
import bitspittle.kross2d.engine.graphics.DrawSurface.ImageParams
import bitspittle.kross2d.engine.graphics.Image
import bitspittle.kross2d.engine.input.Key
import bitspittle.kross2d.extras.anim.Anim
import bitspittle.kross2d.extras.graphics.Tiles

private val CLEAR_COLOR = Color(0, 0, 0)

/**
 * Images: A simple "game" which renders a moving sprite in front of a tiled background.
 *
 * This demo app is intentionally minimal, focusing on basic image asset loading.
 *
 * Demonstrated:
 * - Loading [Image]s using [AssetLoader]
 * - Rendering images using [DrawSurface.drawImage]
 * - Creating and using [Anim]s
 */
class SpriteState : GameState {
    enum class Dir {
        N,
        S,
        E,
        W
    }

    private class Player(private val playerTiles: Tiles) {
        companion object {
            val FRAME_DURATION = Duration.ofMillis(300)
            // See player.png for tiles referenced here (only first row is used so x-coords only)
            val FACING_TO_ANIM = mapOf(
                Dir.N to Anim(FRAME_DURATION, 2, 3),
                Dir.S to Anim(FRAME_DURATION, 0, 1),
                Dir.E to Anim(FRAME_DURATION, 4, 5),
                Dir.W to Anim(FRAME_DURATION, 6, 7)
            )
        }
        private val drawSize = playerTiles.tileSize * 2f // Tweaked until it looked good
        private val pos = Pt2()
        private val vel = Vec2()
        private var currAnim = FACING_TO_ANIM.getValue(Dir.S)

        fun init(ctx: EnterContext) {
            pos.set(drawSize.centerIn(Rect(ctx.screen.size)))
        }

        fun update(ctx: UpdateContext) {
            currAnim.elapse(ctx.timer.lastFrame)

            // Allow up/down and left/right to cancel each other out
            vel.set(Pt2.ZERO)
            if (ctx.keyboard.isDown(Key.UP)) vel.y -= 1f
            if (ctx.keyboard.isDown(Key.DOWN)) vel.y += 1f
            if (ctx.keyboard.isDown(Key.LEFT)) vel.x -= 1f
            if (ctx.keyboard.isDown(Key.RIGHT)) vel.x += 1f
            vel.normalize() // Make sure even diagonal directions move a consistent speed
            // Travel at a speed so you cross the screen in a few seconds
            vel *= (200.0 * ctx.timer.lastFrame.secs).toFloat()

            when {
                vel.x > 0 -> Dir.E
                vel.x < 0 -> Dir.W
                vel.y < 0 -> Dir.N
                vel.y > 0 -> Dir.S
                else -> null
            }?.let { facing ->
                currAnim = FACING_TO_ANIM.getValue(facing)
            }

            (ctx.screen.size - drawSize).let { bounds ->
                pos += vel
                pos.x = pos.x.clamp(0f, bounds.x)
                pos.y = pos.y.clamp(0f, bounds.y)
            }
        }

        fun draw(ctx: DrawContext) {
            val tileX = currAnim.value // We only use the first row of tiles
            ctx.screen.drawImage(playerTiles.getTile(tileX, 0), ImageParams(pos, drawSize))
        }
    }

    private lateinit var grassAsset: Asset<Image>
    private var player: Player? = null

    override fun enter(ctx: EnterContext) {
        grassAsset = ctx.assetLoader.loadImage("grass.png")
        ctx.assetLoader.loadImage("player.png").onLoaded += { image ->
            player = Player(Tiles(image, Vec2(16, 16))).apply { init(ctx) }
        }
    }

    override fun update(ctx: UpdateContext) {
        if (ctx.keyboard.isJustPressed(Key.ESC)) {
            ctx.app.quit()
        }
        player?.update(ctx)
    }

    override fun draw(ctx: DrawContext) {
        ctx.screen.clear(CLEAR_COLOR)

        grassAsset.data?.let { grassTile ->
            val numTiles = ctx.screen.size / grassTile.size
            for (i in 0..numTiles.x.toInt()) {
                for (j in 0..numTiles.y.toInt()) {
                    ctx.screen.drawImage(grassTile, ImageParams(Pt2(grassTile.size.x * i, grassTile.size.y * j)))
                }
            }
        }
        player?.draw(ctx)
    }
}

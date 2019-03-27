import bitspittle.kross2d.core.graphics.Color
import bitspittle.kross2d.core.math.Pt2
import bitspittle.kross2d.core.math.Vec2
import bitspittle.kross2d.core.math.clamp
import bitspittle.kross2d.core.time.Duration
import bitspittle.kross2d.engine.GameState
import bitspittle.kross2d.engine.assets.AssetLoader
import bitspittle.kross2d.engine.context.DrawContext
import bitspittle.kross2d.engine.context.InitContext
import bitspittle.kross2d.engine.context.UpdateContext
import bitspittle.kross2d.engine.graphics.DrawSurface
import bitspittle.kross2d.engine.graphics.DrawSurface.DrawParams
import bitspittle.kross2d.engine.graphics.Image
import bitspittle.kross2d.engine.input.Key
import bitspittle.kross2d.core.geom.Rect
import bitspittle.kross2d.core.geom.centerIn
import bitspittle.kross2d.extras.graphics.Tiles

private val CLEAR_COLOR = Color(0, 0, 0)

/**
 * Images: A simple "game" which renders a moving sprite in front of a tiled background.
 *
 * This demo app is intentionally minimal, focusing on basic image asset loading.
 *
 * Demonstrated:
 * - Loading [Image]s using [AssetLoader]
 * - Rendering images using [DrawSurface.draw]
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
            // See player.png for tiles
            val FACING_TO_TILE_X = mapOf(
                Dir.N to 2,
                Dir.S to 0,
                Dir.E to 4,
                Dir.W to 6
            )
        }
        private val drawSize = playerTiles.tileSize * 2f // Tweaked until it looked good
        private val pos = Pt2()
        private val vel = Vec2()
        private val elapsed = Duration.zero()

        private var facing = Dir.S
        private var animCycle = 0

        fun init(ctx: InitContext) {
            pos.set(drawSize.centerIn(Rect(ctx.screen.size)))
        }

        fun update(ctx: UpdateContext) {
            elapsed += ctx.timer.lastFrameDuration
            while (elapsed > FRAME_DURATION) {
                // All player animations toggle between two frames (e.g. left0/left1, right0/right1, etc.)
                animCycle = (animCycle + 1) % 2
                elapsed -= FRAME_DURATION
            }

            // Allow up/down and left/right to cancel each other out
            vel.set(Pt2.ZERO)
            if (ctx.keyboard.isDown(Key.UP)) vel.y -= 1f
            if (ctx.keyboard.isDown(Key.DOWN)) vel.y += 1f
            if (ctx.keyboard.isDown(Key.LEFT)) vel.x -= 1f
            if (ctx.keyboard.isDown(Key.RIGHT)) vel.x += 1f
            vel.normalize() // Make sure even diagonal directions move a consistent speed
            // Travel at a speed so you cross the screen in a few seconds
            vel *= (200.0 * ctx.timer.lastFrameDuration.secs).toFloat()

            when {
                vel.x > 0 -> facing = Dir.E
                vel.x < 0 -> facing = Dir.W
                vel.y < 0 -> facing = Dir.N
                vel.y > 0 -> facing = Dir.S
            }

            (ctx.screen.size - drawSize).let { bounds ->
                pos += vel
                pos.x = pos.x.clamp(0f, bounds.x)
                pos.y = pos.y.clamp(0f, bounds.y)
            }
        }

        fun draw(ctx: DrawContext) {
            val tileX = FACING_TO_TILE_X.getValue(facing) + animCycle  // We only use the first row of tiles
            ctx.screen.draw(playerTiles.getTile(tileX, 0), DrawParams(pos, drawSize))
        }
    }

    private lateinit var playerSheet: Image
    private lateinit var grassTile: Image
    private lateinit var player: Player

    override fun init(ctx: InitContext) {
        grassTile = ctx.assetLoader.loadImage("grass.png")!!
        playerSheet = ctx.assetLoader.loadImage("player.png")!!
        player = Player(Tiles(playerSheet, Vec2(16, 16))).apply { init(ctx) }
    }

    override fun update(ctx: UpdateContext) {
        if (ctx.keyboard.isDown(Key.ESC)) {
            ctx.app.quit()
        }
        player.update(ctx)
    }

    override fun draw(ctx: DrawContext) {
        ctx.screen.clear(CLEAR_COLOR)

        val numTiles = ctx.screen.size / grassTile.size
        for (i in 0..numTiles.x.toInt()) {
            for (j in 0..numTiles.y.toInt()) {
                ctx.screen.draw(grassTile, DrawSurface.DrawParams(Pt2(grassTile.size.x * i, grassTile.size.y * j)))
            }
        }

        player.draw(ctx)
    }
}

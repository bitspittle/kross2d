import dev.bitspittle.kross2d.core.event.ScopedObserver
import dev.bitspittle.kross2d.core.geom.Rect
import dev.bitspittle.kross2d.core.graphics.Colors
import dev.bitspittle.kross2d.core.math.ImmutableVec2
import dev.bitspittle.kross2d.core.math.Pt2
import dev.bitspittle.kross2d.core.math.Vec2
import dev.bitspittle.kross2d.engine.GameState
import dev.bitspittle.kross2d.engine.context.DrawContext
import dev.bitspittle.kross2d.engine.context.EnterContext
import dev.bitspittle.kross2d.engine.context.UpdateContext
import dev.bitspittle.kross2d.engine.graphics.Font
import dev.bitspittle.kross2d.engine.graphics.Image
import dev.bitspittle.kross2d.extras.ecs.World
import components.*
import objects.ScoreBoard
import objects.Side
import systems.*
import kotlin.random.Random

/**
 * Pong!
 *
 * This demo is copied from the Amethyst engine example, as a way one can compare the difference
 * of how this looks in Rust vs. Kotlin. Rust is the clear choice if you want to squeeze out
 * maximum performance, but Kotlin has more concise syntax that is often easier to read and
 * write.
 *
 * Demonstrated:
 * - Loading [Image]s and [Font]s
 * - Using Kross2D ECS
 * - A full, playable game!
 */
class PongState : GameState {
    companion object {
        val ARENA_SIZE: ImmutableVec2 = Vec2(100, 100)
    }

    private val world = World()
    private val scoreBoard = ScoreBoard()

    override fun enter(ctx: EnterContext) {
        ctx.assetLoader.loadFont("font/square.ttf").onLoaded += ScopedObserver(ctx.scopes.currState) {
            val font = it.derive(16f)
            for (side in listOf(Side.LEFT, Side.RIGHT)) {
                world.createEntity().apply {
                    add(Score(scoreBoard, side))
                    add(Printable(font))
                }
            }
        }
        ctx.assetLoader.loadImage("image/pong.png").onLoaded += ScopedObserver(ctx.scopes.currState) {
            val spritePaddle = it.subimage(Pt2.ZERO, Vec2(4, 16))
            val spriteBall = it.subimage(Pt2(4, 0), Vec2(4, 4))

            Paddle(Side.LEFT).let { paddleL ->
                paddleL.shape.size.set(spritePaddle.size)
                paddleL.shape.y = (ARENA_SIZE.y - paddleL.shape.h) / 2f

                world.createEntity().apply {
                    add(paddleL)
                    add(Body(paddleL.shape))
                    add(Renderable(spritePaddle))
                }
            }

            Paddle(Side.RIGHT).let { paddleR ->
                paddleR.shape.size.set(spritePaddle.size)
                paddleR.shape.x = (ARENA_SIZE.x - paddleR.shape.w)
                paddleR.shape.y = (ARENA_SIZE.y - paddleR.shape.h) / 2f

                world.createEntity().apply {
                    add(paddleR)
                    add(Body(paddleR.shape))
                    add(Renderable(spritePaddle))
                }
            }

            Ball().let { ball ->
                ball.shape.center.set(Rect(ARENA_SIZE).center)
                ball.shape.radius = spriteBall.size.y / 2
                val randomSignX = if (Random.nextBoolean()) 1 else -1
                val randomSignY = if (Random.nextBoolean()) 1 else -1
                ball.vel.set(Vec2( 75 * randomSignX, 50 * randomSignY))

                world.createEntity().apply {
                    add(ball)
                    add(Body(ball.shape))
                    add(Renderable(spriteBall))
                }
            }
        }

        world.addSystem(PaddleSystem(ARENA_SIZE))
        world.addSystem(BallSystem())
        world.addSystem(InputSystem())
        world.addSystem(BounceSystem(ARENA_SIZE, ctx.assetLoader.loadSound("audio/bounce.wav")))
        world.addSystem(ScoreUpdateSystem(ARENA_SIZE, scoreBoard, ctx.assetLoader.loadSound("audio/score.wav")))
        world.addSystem(ScoreDrawSystem(ARENA_SIZE))
        world.addSystem(RenderSystem(ARENA_SIZE))
    }

    override fun update(ctx: UpdateContext) {
        world.update(ctx)
    }

    override fun draw(ctx: DrawContext) {
        ctx.screen.clear(Colors.BLACK)
        world.draw(ctx)
    }
}

package systems

import dev.bitspittle.kross2d.core.math.ImmutableVec2
import dev.bitspittle.kross2d.engine.assets.Asset
import dev.bitspittle.kross2d.engine.audio.Sound
import dev.bitspittle.kross2d.engine.context.UpdateContext
import dev.bitspittle.kross2d.extras.ecs.Entity
import dev.bitspittle.kross2d.extras.ecs.Family
import dev.bitspittle.kross2d.extras.ecs.UpdateSystem
import dev.bitspittle.kross2d.extras.ecs.World
import components.Ball
import components.Body
import components.Paddle
import objects.Side

class BounceSystem(private val arenaSize: ImmutableVec2,
                   private val soundBounce: Asset<Sound>)
    : UpdateSystem(Family.all(Ball::class)) {

    enum class Dir {
        HORIZ,
        VERT
    }

    override fun update(world: World.Facade, ctx: UpdateContext, entity: Entity) {
        val ball = entity.get<Ball>()
        val ballRect = ball.shape.toBoundingRect()
        if ((ballRect.y <= 0f && ball.vel.y < 0f) || (ballRect.y2 >= arenaSize.y && ball.vel.y > 0f)) {
            bounce(ball, Dir.VERT)
        }

        val paddles = world.query(Paddle::class)
        paddles.forEach {
            val paddle = it.get<Paddle>()
            if (paddle.shape.intersects(ballRect)) {
                if ((paddle.side == Side.LEFT && ball.vel.x < 0f)
                    || (paddle.side == Side.RIGHT && ball.vel.x > 0f)
                ) {
                    bounce(ball, Dir.HORIZ)
                }
            }
        }
    }

    private fun bounce(ball: Ball, dir: Dir) {
        if (dir == Dir.HORIZ) {
            ball.vel.x *= -1f
        }
        else if (dir == Dir.VERT) {
            ball.vel.y *= -1f
        }
        soundBounce.data?.play()
    }

}
package systems

import dev.bitspittle.kross2d.engine.context.UpdateContext
import dev.bitspittle.kross2d.engine.input.Key
import dev.bitspittle.kross2d.extras.ecs.Entity
import dev.bitspittle.kross2d.extras.ecs.Family
import dev.bitspittle.kross2d.extras.ecs.UpdateSystem
import dev.bitspittle.kross2d.extras.ecs.World
import components.Paddle
import objects.Side

private const val PADDLE_SPEED = 75f

class InputSystem : UpdateSystem(Family.all(Paddle::class)) {

    override fun update(world: World.Facade, ctx: UpdateContext, entity: Entity) {
        val paddle = entity.get<Paddle>()
        when (paddle.side) {
            Side.LEFT -> {
                when {
                    ctx.keyboard.isDown(Key.W) -> paddle.vel.y = -PADDLE_SPEED
                    ctx.keyboard.isDown(Key.S) -> paddle.vel.y = PADDLE_SPEED
                    else -> paddle.vel.y = 0f
                }
            }
            Side.RIGHT -> {
                when {
                    ctx.keyboard.isDown(Key.UP) -> paddle.vel.y = -PADDLE_SPEED
                    ctx.keyboard.isDown(Key.DOWN) -> paddle.vel.y = PADDLE_SPEED
                    else -> paddle.vel.y = 0f
                }
            }
        }
    }
}
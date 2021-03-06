package systems

import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.core.math.clamp
import bitspittle.kross2d.engine.context.UpdateContext
import bitspittle.kross2d.extras.ecs.Entity
import bitspittle.kross2d.extras.ecs.Family
import bitspittle.kross2d.extras.ecs.UpdateSystem
import bitspittle.kross2d.extras.ecs.World
import components.Paddle

class PaddleSystem(private val arenaSize: ImmutableVec2) : UpdateSystem(Family.all(Paddle::class)) {
    override fun update(world: World.Facade, ctx: UpdateContext, entity: Entity) {
        val paddle = entity.get<Paddle>()

        paddle.shape.pos += (paddle.vel * ctx.timer.lastFrame.secs.toFloat())
        paddle.shape.y = paddle.shape.y.clamp(0f, arenaSize.y - paddle.shape.h)
    }
}
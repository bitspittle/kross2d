package systems

import bitspittle.kross2d.engine.context.UpdateContext
import bitspittle.kross2d.extras.ecs.Entity
import bitspittle.kross2d.extras.ecs.Family
import bitspittle.kross2d.extras.ecs.UpdateSystem
import bitspittle.kross2d.extras.ecs.World
import components.Ball

class BallSystem : UpdateSystem(Family.all(Ball::class)) {
    override fun update(world: World.Facade, ctx: UpdateContext, entity: Entity) {
        val ball = entity.get<Ball>()
        ball.shape.center += (ball.vel * ctx.timer.lastFrame.secs.toFloat())
    }
}
package systems

import dev.bitspittle.kross2d.engine.context.UpdateContext
import dev.bitspittle.kross2d.extras.ecs.Entity
import dev.bitspittle.kross2d.extras.ecs.Family
import dev.bitspittle.kross2d.extras.ecs.UpdateSystem
import dev.bitspittle.kross2d.extras.ecs.World
import components.Ball

class BallSystem : UpdateSystem(Family.all(Ball::class)) {
    override fun update(world: World.MutableFacade, ctx: UpdateContext, entity: Entity) {
        val ball = entity.get<Ball>()
        ball.shape.center += (ball.vel * ctx.timer.lastFrame.secs.toFloat())
    }
}
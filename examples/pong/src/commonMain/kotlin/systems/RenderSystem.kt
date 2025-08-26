package systems

import components.Body
import components.Renderable
import dev.bitspittle.kross2d.core.math.Vec2
import dev.bitspittle.kross2d.engine.context.DrawContext
import dev.bitspittle.kross2d.engine.graphics.DrawSurface.ImageParams
import dev.bitspittle.kross2d.extras.ecs.DrawSystem
import dev.bitspittle.kross2d.extras.ecs.Entity
import dev.bitspittle.kross2d.extras.ecs.Family
import dev.bitspittle.kross2d.extras.ecs.World

class RenderSystem(private val worldSize: Vec2) :
    DrawSystem(Family.all(Renderable::class, Body::class)) {

    private lateinit var scale: Vec2

    override fun beforeDraws(world: World.Facade, ctx: DrawContext) {
        scale = ctx.screen.size / worldSize
    }

    override fun draw(world: World.Facade, ctx: DrawContext, entity: Entity) {
        val image = entity.get<Renderable>().image
        val rect = entity.get<Body>().shape.toBoundingRect()

        ctx.screen.drawImage(image, ImageParams(dest = rect.pos * scale, destSize = rect.size * scale))
    }
}
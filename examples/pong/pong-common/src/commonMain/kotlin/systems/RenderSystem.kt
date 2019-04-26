package systems

import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.engine.context.DrawContext
import bitspittle.kross2d.engine.graphics.DrawSurface.ImageParams
import bitspittle.kross2d.extras.ecs.DrawSystem
import bitspittle.kross2d.extras.ecs.Entity
import bitspittle.kross2d.extras.ecs.Family
import bitspittle.kross2d.extras.ecs.World
import components.Body
import components.Renderable

class RenderSystem(private val worldSize: ImmutableVec2) :
    DrawSystem(Family.all(Renderable::class, Body::class)) {

    private lateinit var scale: ImmutableVec2

    override fun beforeDraws(world: World.ImmutableFacade, ctx: DrawContext) {
        scale = ctx.screen.size / worldSize
    }

    override fun draw(world: World.ImmutableFacade, ctx: DrawContext, entity: Entity) {
        val image = entity.get<Renderable>().image
        val rect = entity.get<Body>().shape.toBoundingRect()

        ctx.screen.drawImage(image, ImageParams(dest = rect.pos * scale, destSize = rect.size * scale))
    }
}
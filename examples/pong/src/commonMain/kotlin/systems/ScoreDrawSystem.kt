package systems

import components.Printable
import components.Score
import dev.bitspittle.kross2d.core.math.Pt2
import dev.bitspittle.kross2d.core.math.Vec2
import dev.bitspittle.kross2d.engine.context.DrawContext
import dev.bitspittle.kross2d.engine.graphics.DrawSurface
import dev.bitspittle.kross2d.engine.graphics.DrawSurface.TextParams.Anchor
import dev.bitspittle.kross2d.extras.ecs.DrawSystem
import dev.bitspittle.kross2d.extras.ecs.Entity
import dev.bitspittle.kross2d.extras.ecs.Family
import dev.bitspittle.kross2d.extras.ecs.World
import objects.Side

class ScoreDrawSystem(private val worldSize: Vec2) :
    DrawSystem(Family.all(Printable::class, Score::class)) {

    private lateinit var scale: Vec2

    private val xLeft = worldSize.x * 4f / 10f
    private val xRight = worldSize.x * 6f / 10f
    private val y = worldSize.y / 8f

    override fun beforeDraws(world: World.Facade, ctx: DrawContext) {
        scale = ctx.screen.size / worldSize
    }

    override fun draw(world: World.Facade, ctx: DrawContext, entity: Entity) {
        val printable = entity.get<Printable>()
        val score = entity.get<Score>()

        val x = if (score.side == Side.LEFT) xLeft else xRight

        ctx.screen.drawText(
            printable.font,
            "${score.value}",
            DrawSurface.TextParams(pt = Pt2(x, y) * scale, anchor = Anchor.CENTER))
    }
}
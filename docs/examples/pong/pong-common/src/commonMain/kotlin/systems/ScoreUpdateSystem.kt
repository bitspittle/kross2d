package systems

import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.engine.assets.Asset
import bitspittle.kross2d.engine.audio.Sound
import bitspittle.kross2d.engine.context.UpdateContext
import bitspittle.kross2d.extras.ecs.Entity
import bitspittle.kross2d.extras.ecs.Family
import bitspittle.kross2d.extras.ecs.UpdateSystem
import bitspittle.kross2d.extras.ecs.World
import components.Ball
import objects.ScoreBoard

class ScoreUpdateSystem(private val arenaSize: ImmutableVec2,
                        private val scoreBoard: ScoreBoard,
                        private val soundScore: Asset<Sound>)
    : UpdateSystem(Family.all(Ball::class)) {

    override fun update(world: World.Facade, ctx: UpdateContext, entity: Entity) {
        val ball = entity.get<Ball>()
        val ballRect = ball.shape.toBoundingRect()

        val scored =
            when {
                ballRect.x <= 0f -> {
                    scoreBoard.right++
                    true
                }
                ballRect.x2 >= arenaSize.x -> {
                    scoreBoard.left++
                    true
                }
                else -> false
            }

        if (scored) {
            soundScore.data?.play()
            ball.vel.x = -ball.vel.x
            ball.shape.center.x = arenaSize.x / 2f
        }
    }

}
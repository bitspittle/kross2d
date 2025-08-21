package components

import dev.bitspittle.kross2d.extras.ecs.Component
import objects.ScoreBoard
import objects.Side

class Score(private val scoreBoard: ScoreBoard, val side: Side) : Component {
    val value
        get() = if (side == Side.LEFT) scoreBoard.left else scoreBoard.right
}
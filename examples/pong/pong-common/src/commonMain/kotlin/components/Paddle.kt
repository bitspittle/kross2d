package components

import dev.bitspittle.kross2d.core.geom.Rect
import dev.bitspittle.kross2d.core.math.Vec2
import dev.bitspittle.kross2d.extras.ecs.Component
import objects.Side

class Paddle(val side: Side) : Component {
    val shape = Rect()
    val vel = Vec2()
}
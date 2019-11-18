package components

import bitspittle.kross2d.core.geom.Rect
import bitspittle.kross2d.core.math.Vec2
import bitspittle.kross2d.extras.ecs.Component
import objects.Side

class Paddle(val side: Side) : Component {
    val shape = Rect()
    val vel = Vec2()
}
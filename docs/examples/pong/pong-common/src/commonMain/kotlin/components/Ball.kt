package components

import bitspittle.kross2d.core.geom.Circle
import bitspittle.kross2d.core.math.Vec2
import bitspittle.kross2d.extras.ecs.Component

class Ball : Component {
    val shape = Circle()
    val vel = Vec2()
}
package components

import dev.bitspittle.kross2d.core.geom.Circle
import dev.bitspittle.kross2d.core.math.Vec2
import dev.bitspittle.kross2d.extras.ecs.Component

class Ball : Component {
    val shape = Circle()
    val vel = Vec2()
}
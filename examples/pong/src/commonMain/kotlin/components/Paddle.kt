package components

import dev.bitspittle.kross2d.core.geom.MutableRect
import dev.bitspittle.kross2d.core.math.MutableVec2
import dev.bitspittle.kross2d.extras.ecs.Component
import objects.Side

class Paddle(val side: Side) : Component {
    val shape = MutableRect()
    val vel = MutableVec2()
}
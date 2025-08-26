package components

import dev.bitspittle.kross2d.core.geom.MutableCircle
import dev.bitspittle.kross2d.core.math.MutableVec2
import dev.bitspittle.kross2d.extras.ecs.Component

class Ball : Component {
    val shape = MutableCircle()
    val vel = MutableVec2()
}
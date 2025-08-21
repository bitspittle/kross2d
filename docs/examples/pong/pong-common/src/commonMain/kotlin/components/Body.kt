package components

import dev.bitspittle.kross2d.core.geom.ImmutableShape
import dev.bitspittle.kross2d.extras.ecs.Component

class Body(val shape: ImmutableShape) : Component
package components

import bitspittle.kross2d.core.geom.ImmutableShape
import bitspittle.kross2d.extras.ecs.Component

class Body(val shape: ImmutableShape) : Component
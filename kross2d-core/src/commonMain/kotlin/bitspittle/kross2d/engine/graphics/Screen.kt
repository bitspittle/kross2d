package bitspittle.kross2d.engine.graphics

import bitspittle.kross2d.core.math.ImmutableVec2

/**
 * Read-only properties of a [Screen]
 */
interface ImmutableScreen : ImmutableDrawSurface

/**
 * The final screen area that the user will see.
 *
 * TODO: Consider removing this class if its functionality can safely be pushed up to DrawSurface.
 *  For now, I'm not sure how difficult it will be to implement this for off-screen textures on
 *  both web and desktop, but if it's do-able, no need for an extra interface.
 */
interface Screen : ImmutableScreen, DrawSurface {
    sealed class Transform {
        class Scale(val x: Double, val y: Double) : Transform() {
            constructor(vec2: ImmutableVec2): this(vec2.x.toDouble(), vec2.y.toDouble())
        }
        class Translate(val x: Double, val y: Double) : Transform() {
            constructor(vec2: ImmutableVec2): this(vec2.x.toDouble(), vec2.y.toDouble())
        }
        class Composite(val lhs: Transform, val rhs: Transform) : Transform()

        operator fun plus(rhs: Transform) = Composite(this, rhs)
    }

    fun pushTransform(transform: Transform)
    fun popTransform()
}

inline fun Screen.withTransform(transform: Screen.Transform, block: (Screen) -> Unit) {
    pushTransform(transform)
    try {
        block(this)
    }
    finally {
        popTransform()
    }
}

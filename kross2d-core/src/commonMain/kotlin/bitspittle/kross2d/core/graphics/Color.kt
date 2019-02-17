package bitspittle.kross2d.core.graphics

import kotlin.math.min

/**
 * An immutable view of a [Color]
 */
interface ImmutableColor {
    val r: Int
    val g: Int
    val b: Int
    val a: Int
}

/**
 * A 32-bit color, with 8-bits used each for R, G, B, and A.
 */
data class Color constructor(override var r: Int, override var g: Int, override var b: Int, override var a: Int = 255) :
    ImmutableColor {
    constructor(r: Float, g: Float, b: Float, a: Float = 1.0f) : this(
        (255 * r).toInt(), (255 * g).toInt(), (255 * b).toInt(), (255 * a).toInt()
    )

    init {
        this.r = min(r, 255)
        this.g = min(g, 255)
        this.b = min(b, 255)
        this.a = min(a, 255)
    }

    companion object {
        const val A_MASK: Int = 0xFF000000.toInt() // toInt required to avoid being treated as Long
        const val R_MASK: Int = 0x00FF0000
        const val G_MASK: Int = 0x0000FF00
        const val B_MASK: Int = 0x000000FF

        fun fromArgb(argb: Int) = Color(
            argb.and(R_MASK),
            argb.and(G_MASK),
            argb.and(B_MASK),
            argb.and(A_MASK)
        )
    }

    val argb: Int
        get() = a.shl(24).or(r.shl(16)).or(g.shl(8)).or(b)
}

package dev.bitspittle.kross2d.core.graphics

import dev.bitspittle.kross2d.core.math.clamp
import kotlin.math.roundToInt

private const val A_MASK: Int = 0xFF000000.toInt() // toInt required to avoid being treated as Long
private const val R_MASK: Int = 0x00FF0000
private const val G_MASK: Int = 0x0000FF00
private const val B_MASK: Int = 0x000000FF

private const val A_POS: Int = 24
private const val R_POS: Int = 16
private const val G_POS: Int = 8
private const val B_POS: Int = 0

internal interface ColorFactory<C: Color> {
    fun fromRgb(rgb: Int): C
}

/**
 * A 32-bit color, with 8-bits used each for R, G, B, and A.
 */
interface Color {
    companion object : ColorFactory<Color> {
        override fun fromRgb(rgb: Int): Color = MutableColor.fromRgb(rgb)
    }

    var argb: Int
    val rgb: Int get() = argb.and(A_MASK.inv())

    // NOTE: ushr vs. shr avoids carrying over negative sign. For example, 0xFF000000 as an Int is a negative number,
    // but for encoding colors that doesn't really apply. Shifting 0xFF000000 over, we want 0x000000FF, not
    // 0xFFFFFFFF which we'll get if we use shr.
    val r: Int get() = argb.and(R_MASK).ushr(R_POS)
    val g: Int get() = argb.and(G_MASK).ushr(G_POS)
    val b: Int get() = argb.and(B_MASK).ushr(B_POS)
    val a: Int get() = argb.and(A_MASK).ushr(A_POS)

    fun toColor() = Color(argb)
    fun toMutableColor() = MutableColor(argb)
}

fun Color(argb: Int): Color = MutableColor(argb)
fun Color(r: Int, g: Int, b: Int, a: Int = 255): Color = MutableColor(r, g, b, a)
fun Color(r: Float, g: Float, b: Float, a: Float = 1.0f): Color = MutableColor(r, g, b, a)

private fun Int.colorClamped() = this.clamp(0, 255)
private fun Float.toColorInt() = (255 * this.clamp(0f, 1f)).roundToInt()

class MutableColor(override var argb: Int) : Color {
    constructor(r: Int, g: Int, b: Int, a: Int = 255) : this(a.colorClamped().shl(A_POS).or(r.colorClamped().shl(R_POS)).or(g.colorClamped().shl(G_POS)).or(b.colorClamped().shl(B_POS)))
    constructor(r: Float, g: Float, b: Float, a: Float = 1.0f) : this(r.toColorInt(), g.toColorInt(), b.toColorInt(), a.toColorInt())

    companion object : ColorFactory<MutableColor> {
        override fun fromRgb(rgb: Int) = MutableColor(0xFF.shl(A_POS).and(rgb))
    }

    override var r: Int
        get() = super.r
        set(value) {
            argb = (argb.and(R_MASK.inv())).or(value.colorClamped().shl(R_POS))
        }

    override var g: Int
        get() = super.g
        set(value) {
            argb = (argb.and(G_MASK.inv())).or(value.colorClamped().shl(G_POS))
        }

    override var b: Int
        get() = super.b
        set(value) {
            argb = (argb.and(B_MASK.inv())).or(value.colorClamped().shl(B_POS))
        }

    override var a: Int
        get() = super.a
        set(value) {
            argb = (argb.and(A_MASK.inv())).or(value.colorClamped().shl(A_POS))
        }

    fun setFrom(other: Color) {
        argb = other.argb
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Color) argb == other.argb else false
    }

    override fun hashCode(): Int {
        return argb.hashCode()
    }

    override fun toString() = "Color { 0x${argb.toUInt().toHexString().uppercase()} (r=$r, g=$g, b=$b, a=$a) }"
}

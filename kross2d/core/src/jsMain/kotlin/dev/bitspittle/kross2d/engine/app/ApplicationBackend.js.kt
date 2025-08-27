package dev.bitspittle.kross2d.engine.app

import dev.bitspittle.kross2d.core.event.Event
import dev.bitspittle.kross2d.core.event.EventEmitter
import dev.bitspittle.kross2d.core.graphics.Color
import dev.bitspittle.kross2d.core.graphics.Colors
import dev.bitspittle.kross2d.core.math.MutablePt2
import dev.bitspittle.kross2d.core.math.Pt2
import dev.bitspittle.kross2d.core.math.Vec2
import dev.bitspittle.kross2d.engine.graphics.DrawSurface
import dev.bitspittle.kross2d.engine.graphics.DrawSurface.ImageParams
import dev.bitspittle.kross2d.engine.graphics.Font
import dev.bitspittle.kross2d.engine.graphics.Image
import dev.bitspittle.kross2d.engine.graphics.MutableScreen
import dev.bitspittle.kross2d.engine.graphics.Screen.Transform
import dev.bitspittle.kross2d.engine.graphics.Screen.Transform.*
import dev.bitspittle.kross2d.engine.input.Button
import dev.bitspittle.kross2d.engine.input.Key
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.MouseEvent
import kotlin.math.roundToInt

fun Color.toHtmlColor() = "rgb($r, $g, $b)"
fun Font.toHtmlFont() = "${fontData.size.roundToInt()}px ${fontData.name}"

actual class AppParams(
    val canvasElement: HTMLCanvasElement,
    actual val assetsRoot: String = "assets")

internal actual class ApplicationBackend actual constructor(params: AppParams) {
    private val ctx: CanvasRenderingContext2D = (params.canvasElement.getContext("2d") as CanvasRenderingContext2D)

    init {
        ctx.imageSmoothingEnabled = false

        fun handleKeyEvent(keyEvent: KeyboardEvent, isDown: Boolean) {
            // Make sure canvas owns all keypresses. That is, don't let tab, space, etc. transfer
            // focus elsewhere.
            keyEvent.preventDefault()

//            println(keyEvent.code) // Useful to uncomment occasionally to see JS key values
            when (keyEvent.code) {
                "Escape" -> Key.ESC

                "ArrowUp" -> Key.UP
                "ArrowDown" -> Key.DOWN
                "ArrowLeft" -> Key.LEFT
                "ArrowRight" -> Key.RIGHT

                "Digit0" -> Key.NUM_0
                "Digit1" -> Key.NUM_1
                "Digit2" -> Key.NUM_2
                "Digit3" -> Key.NUM_3
                "Digit4" -> Key.NUM_4
                "Digit5" -> Key.NUM_5
                "Digit6" -> Key.NUM_6
                "Digit7" -> Key.NUM_7
                "Digit8" -> Key.NUM_8
                "Digit9" -> Key.NUM_9

                "KeyA" -> Key.A
                "KeyB" -> Key.B
                "KeyC" -> Key.C
                "KeyD" -> Key.D
                "KeyE" -> Key.E
                "KeyF" -> Key.F
                "KeyG" -> Key.G
                "KeyH" -> Key.H
                "KeyI" -> Key.I
                "KeyJ" -> Key.J
                "KeyK" -> Key.K
                "KeyL" -> Key.L
                "KeyM" -> Key.M
                "KeyN" -> Key.N
                "KeyO" -> Key.O
                "KeyP" -> Key.P
                "KeyQ" -> Key.Q
                "KeyR" -> Key.R
                "KeyS" -> Key.S
                "KeyT" -> Key.T
                "KeyU" -> Key.U
                "KeyV" -> Key.V
                "KeyW" -> Key.W
                "KeyX" -> Key.X
                "KeyY" -> Key.Y
                "KeyZ" -> Key.Z

                "Space" -> Key.SPACE
                "Tab" -> Key.TAB
                "Backspace" -> Key.BACKSPACE
                "Enter" -> Key.ENTER

                else -> null
            }?.let { key -> if (isDown) _keyPressed(key) else _keyReleased(key) }
        }
        window.onkeydown = { handleKeyEvent(it, isDown = true) }
        window.onkeyup = { handleKeyEvent(it, isDown = false) }

        val mousePos = MutablePt2()
        fun handleMouseMoveEvent(mouseEvent: MouseEvent) {
            val x = mouseEvent.clientX - ctx.canvas.offsetLeft
            val y = mouseEvent.clientY - ctx.canvas.offsetTop

            mousePos.set(x, y)
            _mouseMoved(mousePos)
        }
        fun handleMouseButtonEvent(mouseEvent: MouseEvent, isDown: Boolean) {
            val button = when(mouseEvent.button.toInt()) {
                0 -> Button.LEFT
                1 -> Button.MIDDLE
                2 -> Button.RIGHT
                else -> return
            }
            if (isDown) _buttonPressed(button) else _buttonReleased(button)
        }

        ctx.canvas.onmousemove = { handleMouseMoveEvent(it)}
        ctx.canvas.onmousedown = { handleMouseButtonEvent(it, isDown = true) }
        ctx.canvas.onmouseup = { handleMouseButtonEvent(it, isDown = false) }
    }

    actual val screen: MutableScreen = object : MutableScreen {
        override val size: Vec2 = params.canvasElement.let { Vec2(it.width, it.height) }

        override fun clear(color: Color) {
            ctx.fillStyle = color.toHtmlColor()
            ctx.fillRect(0.0, 0.0, size.x.toDouble(), size.y.toDouble())
        }

        override fun pushTransform(transform: Transform) {
            ctx.save()
            applyTransform(transform)
        }

        override fun popTransform() {
            ctx.restore()
        }

        private fun applyTransform(transform: Transform) {
            when (transform) {
                is Scale -> ctx.scale(transform.x, transform.y)
                is Translate -> ctx.translate(transform.x, transform.y)
                is Composite -> { applyTransform(transform.lhs); applyTransform(transform.rhs) }
            }
        }

        override fun drawLine(pt1: Pt2, pt2: Pt2, color: Color) {
            ctx.beginPath()
            ctx.strokeStyle = color.toHtmlColor()
            ctx.moveTo(pt1.x.toDouble(), pt1.y.toDouble())
            ctx.lineTo(pt2.x.toDouble(), pt2.y.toDouble())
            ctx.stroke()
        }

        override fun drawImage(image: Image, params: ImageParams) {
            val dest = params.dest
            val src = image.pos
            val srcSize = image.size
            val destSize = params.destSize ?: srcSize
            ctx.drawImage(
                image.data.jsImage,
                src.x.toDouble(), src.y.toDouble(), srcSize.x.toDouble(), srcSize.y.toDouble(),
                dest.x.toDouble(), dest.y.toDouble(), destSize.x.toDouble(), destSize.y.toDouble()
            )
        }

        override fun measureText(font: Font, text: String): Float {
            ctx.font = font.toHtmlFont()
            return ctx.measureText(text).width.toFloat()
        }

        override fun drawText(font: Font, text: String, params: DrawSurface.TextParams) {
            ctx.fillStyle = params.color.toHtmlColor()
            ctx.font = font.toHtmlFont()

            val lines = text.split('\n')
            val startPt = params.toTopLeft(
                produceWidth = { lines.maxOfOrNull { line -> measureText(font, line) } ?: 0f },
                produceHeight = { lines.size * font.size + (lines.size - 1) * params.spacing })

            val x = startPt.x.toDouble()
            var y = startPt.y.toDouble() + font.size // ctx.fillText y is underneath text
            lines.forEach { line ->
                ctx.fillText(line, x, y)
                y += (params.spacing + font.size)
            }
        }
    }

    private val _keyPressed = EventEmitter<Key>()
    actual val keyPressed: Event<Key> = _keyPressed

    private val _keyReleased = EventEmitter<Key>()
    actual val keyReleased: Event<Key> = _keyReleased

    private val _mouseMoved = EventEmitter<Pt2>()
    actual val mouseMoved: Event<Pt2> = _mouseMoved

    private val _buttonPressed = EventEmitter<Button>()
    actual val buttonPressed: Event<Button> = _buttonPressed

    private val _buttonReleased = EventEmitter<Button>()
    actual val buttonReleased: Event<Button> = _buttonReleased

    private var frameStepHandle: Int = 0
    private lateinit var quitBlock: () -> Unit

    actual fun runForever(frameStep: () -> Unit) {
        frameStepHandle = window.setInterval(frameStep, 16)
    }

    actual fun onQuit(quitBlock: () -> Unit) {
        this.quitBlock = quitBlock
    }

    actual fun quit() {
        window.clearInterval(frameStepHandle)
        // Enqueue final instructions, instead of running them immediately, to ensure they get run
        // after any final frames that might still be in the pipeline
        window.setTimeout({
            screen.clear(Colors.Black)
            quitBlock()
        }, 0)
    }
}
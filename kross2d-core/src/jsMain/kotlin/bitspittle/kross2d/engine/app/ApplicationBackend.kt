package bitspittle.kross2d.engine.app

import bitspittle.kross2d.core.event.Event
import bitspittle.kross2d.core.event.ObservableEvent
import bitspittle.kross2d.core.graphics.Colors
import bitspittle.kross2d.core.graphics.ImmutableColor
import bitspittle.kross2d.core.math.ImmutablePt2
import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.core.math.Pt2
import bitspittle.kross2d.core.math.Vec2
import bitspittle.kross2d.engine.graphics.DrawSurface
import bitspittle.kross2d.engine.graphics.DrawSurface.ImageParams
import bitspittle.kross2d.engine.graphics.Font
import bitspittle.kross2d.engine.graphics.Image
import bitspittle.kross2d.engine.graphics.Screen
import bitspittle.kross2d.engine.graphics.Screen.Transform
import bitspittle.kross2d.engine.graphics.Screen.Transform.*
import bitspittle.kross2d.engine.input.Button
import bitspittle.kross2d.engine.input.Key
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.MouseEvent
import kotlin.browser.window
import kotlin.math.roundToInt

fun ImmutableColor.toHtmlColor() = "rgb($r, $g, $b)"
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

        val mousePos = Pt2()
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

    actual val screen: Screen = object : Screen {
        override val size: ImmutableVec2 = params.canvasElement.let { Vec2(it.width, it.height) }

        override fun clear(color: ImmutableColor) {
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

        override fun drawLine(pt1: ImmutablePt2, pt2: ImmutablePt2, color: ImmutableColor) {
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
                produceWidth = { lines.map { line -> measureText(font, line) }.max() ?: 0f },
                produceHeight = { lines.size * font.size + (lines.size - 1) * params.spacing })

            val x = startPt.x.toDouble()
            var y = startPt.y.toDouble() + font.size // ctx.fillText y is underneath text
            lines.forEach { line ->
                ctx.fillText(line, x, y)
                y += (params.spacing + font.size)
            }
        }
    }

    private val _keyPressed = Event<Key>()
    actual val keyPressed: ObservableEvent<Key> = _keyPressed

    private val _keyReleased = Event<Key>()
    actual val keyReleased: ObservableEvent<Key> = _keyReleased

    private val _mouseMoved = Event<ImmutablePt2>()
    actual val mouseMoved: ObservableEvent<ImmutablePt2> = _mouseMoved

    private val _buttonPressed = Event<Button>()
    actual val buttonPressed: ObservableEvent<Button> = _buttonPressed

    private val _buttonReleased = Event<Button>()
    actual val buttonReleased: ObservableEvent<Button> = _buttonReleased

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
            screen.clear(Colors.BLACK)
            quitBlock()
        }, 0)
    }
}
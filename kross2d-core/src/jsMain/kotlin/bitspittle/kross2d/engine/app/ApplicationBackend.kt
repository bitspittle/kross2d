package bitspittle.kross2d.engine.app

import bitspittle.kross2d.core.event.Event
import bitspittle.kross2d.core.event.ObservableEvent
import bitspittle.kross2d.core.graphics.Color
import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.core.math.Vec2
import bitspittle.kross2d.engine.graphics.DrawSurface
import bitspittle.kross2d.engine.graphics.Image
import bitspittle.kross2d.engine.input.Key
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.KeyboardEvent
import kotlin.browser.window
import org.khronos.webgl.WebGLRenderingContext as GL

actual class AppParams(
    val canvasElement: HTMLCanvasElement,
    actual val assetsRoot: String = "assets")

internal actual class ApplicationBackend actual constructor(params: AppParams) {
    private val ctx: CanvasRenderingContext2D = (params.canvasElement.getContext("2d") as CanvasRenderingContext2D)

    init {
        ctx.imageSmoothingEnabled = false

        fun handleKeyEvent(keyEvent: KeyboardEvent, isDown: Boolean) {
//            println(keyEvent.code)
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

                "Space" -> Key.SPACE

                else -> null
            }?.let { key -> if (isDown) _keyPressed(key) else _keyReleased(key) }
        }
        window.onkeydown = { handleKeyEvent(it, isDown = true) }
        window.onkeyup = { handleKeyEvent(it, isDown = false) }
    }

    actual val drawSurface: DrawSurface = object : DrawSurface {
        override val size: ImmutableVec2 = params.canvasElement.let { Vec2(it.width, it.height) }

        override fun clear(color: Color) {
            ctx.fillStyle = "rgb(${color.r}, ${color.g}, ${color.b})"
            ctx.fillRect(0.0, 0.0, size.x.toDouble(), size.y.toDouble())
        }

        override fun draw(image: Image, params: DrawSurface.DrawParams) {
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
    }
    private val _keyPressed = Event<Key>()
    actual val keyPressed: ObservableEvent<Key> = _keyPressed

    private val _keyReleased = Event<Key>()
    actual val keyReleased: ObservableEvent<Key> = _keyReleased

    actual fun runForever(frameStep: () -> Unit) {
        window.setInterval(frameStep, 16)
    }

    actual fun quit() {
        // On the Web, you can never quit. Just close the browser window!
    }
}
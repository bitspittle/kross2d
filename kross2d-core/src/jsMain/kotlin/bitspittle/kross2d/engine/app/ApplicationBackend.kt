package bitspittle.kross2d.engine.app

import bitspittle.kross2d.core.event.Event
import bitspittle.kross2d.core.event.ObservableEvent
import bitspittle.kross2d.core.graphics.Color
import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.core.math.Vec2
import bitspittle.kross2d.engine.graphics.DrawSurface
import bitspittle.kross2d.engine.input.Key
import org.khronos.webgl.WebGLRenderingContext as GL
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.KeyboardEvent
import kotlin.browser.window

actual class AppParams(val canvasElement: HTMLCanvasElement)

internal actual class ApplicationBackend actual constructor(params: AppParams) {
    private val gl: GL = (params.canvasElement.getContext("webgl") as GL)

    init {
        fun handleKeyEvent(keyEvent: KeyboardEvent, isDown: Boolean) {
            when (keyEvent.code) {
                "Escape" -> Key.ESC
                else -> null
            }?.let { key -> if (isDown) _keyPressed(key) else _keyReleased(key) }
        }

        window.onkeydown = { handleKeyEvent(it, isDown = true) }
        window.onkeyup = { handleKeyEvent(it, isDown = false) }
    }

    actual val drawSurface: DrawSurface = object : DrawSurface {
        override val size: ImmutableVec2 = params.canvasElement.let { Vec2(it.width, it.height) }

        override fun clear(color: Color) {
            gl.clearColor(
                color.r.toFloat() / 255.0f,
                color.g.toFloat() / 255.0f,
                color.b.toFloat() / 255.0f,
                color.a.toFloat() / 255.0f
            )
            gl.clear(GL.COLOR_BUFFER_BIT)
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
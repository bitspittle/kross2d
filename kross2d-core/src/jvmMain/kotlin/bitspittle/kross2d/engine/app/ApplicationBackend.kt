package bitspittle.kross2d.engine.app

import bitspittle.kross2d.core.event.Event
import bitspittle.kross2d.core.event.ObservableEvent
import bitspittle.kross2d.core.graphics.Color
import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.engine.graphics.DrawSurface
import bitspittle.kross2d.engine.input.Key
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.WindowConstants
import kotlin.math.roundToInt
import java.awt.Color as AwtColor

fun Color.toAwtColor() = AwtColor(r, g, b, a)

fun ImmutableVec2.toDimension() = Dimension(x.roundToInt(), y.roundToInt())

internal actual class ApplicationBackend actual constructor(params: AppParams) {
    private val frame: JFrame
    private val screen: Screen

    init {
        System.setProperty("sun.java2d.opengl", "true") // For hardware accelerated rendering
        frame = JFrame(params.title)
        frame.defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE // Handle manually
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                quit()
            }
        })

        screen = Screen(params.size)
        frame.contentPane.add(screen)
        frame.isResizable = false
        frame.pack()
        frame.setLocationRelativeTo(null) // This centers the frame on the Window

        frame.addKeyListener(object : KeyAdapter() {
            fun handleAwtKey(awtKey: Int, isDown: Boolean) {
                when (awtKey) {
                    KeyEvent.VK_ESCAPE -> Key.ESC
                    else -> null
                }?.let { key -> if (isDown) _keyPressed(key) else _keyReleased(key) }
            }

            override fun keyPressed(e: KeyEvent) {
                handleAwtKey(e.keyCode, true)
            }

            override fun keyReleased(e: KeyEvent) {
                handleAwtKey(e.keyCode, false)
            }
        })

        frame.isVisible = true
    }

    actual val drawSurface: DrawSurface = screen

    private val _keyPressed = Event<Key>()
    actual val keyPressed: ObservableEvent<Key> = _keyPressed

    private val _keyReleased = Event<Key>()
    actual val keyReleased: ObservableEvent<Key> = _keyReleased

    actual fun runForever(frameStep: () -> Unit) {
        while (frame.isVisible) {
            SwingUtilities.invokeAndWait(frameStep)
        }
    }

    actual fun quit() {
        frame.dispose()
    }

    private class Screen(override val size: ImmutableVec2) : JPanel(), DrawSurface {
        /**
         * Buffer of draw commands. We receive requests to draw to the screen outside of a time we
         * can actually draw them, so we save them up until the next paint happens.
         */
        private val drawCommands = mutableListOf<(Graphics2D) -> Unit>()

        init {
            preferredSize = size.toDimension()
        }

        private fun enqueueCommand(command: (Graphics2D) -> Unit) {
            drawCommands.add(command)
            if (drawCommands.size == 1) {
                repaint() // Schedules this component for repainting
            }
        }

        override fun clear(color: Color) {
            enqueueCommand { g ->
                g.background = color.toAwtColor()
                g.clearRect(0, 0, width, height)
            }
        }

        override fun paintComponent(g: Graphics) {
            val g2d = g as Graphics2D
            drawCommands.forEach { command -> command(g2d) }
            drawCommands.clear()
        }
    }
}

actual class AppParams(val title: String, val size: ImmutableVec2)
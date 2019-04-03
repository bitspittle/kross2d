package bitspittle.kross2d.engine.app

import bitspittle.kross2d.core.event.Event
import bitspittle.kross2d.core.event.ObservableEvent
import bitspittle.kross2d.core.graphics.Color
import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.engine.graphics.DrawSurface
import bitspittle.kross2d.engine.graphics.Image
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

                    KeyEvent.VK_UP -> Key.UP
                    KeyEvent.VK_DOWN -> Key.DOWN
                    KeyEvent.VK_LEFT -> Key.LEFT
                    KeyEvent.VK_RIGHT -> Key.RIGHT

                    KeyEvent.VK_0 -> Key.NUM_0
                    KeyEvent.VK_1 -> Key.NUM_1
                    KeyEvent.VK_2 -> Key.NUM_2
                    KeyEvent.VK_3 -> Key.NUM_3
                    KeyEvent.VK_4 -> Key.NUM_4
                    KeyEvent.VK_5 -> Key.NUM_5
                    KeyEvent.VK_6 -> Key.NUM_6
                    KeyEvent.VK_7 -> Key.NUM_7
                    KeyEvent.VK_8 -> Key.NUM_8
                    KeyEvent.VK_9 -> Key.NUM_9

                    KeyEvent.VK_SPACE -> Key.SPACE

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

    actual fun onQuit(quitBlock: () -> Unit) {
        // JVM blocks in runForever until quit is called, so by the time we get here, we can safely
        // call quitBlock directly.
        quitBlock()
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

        override fun draw(image: Image, params: DrawSurface.DrawParams) {
            enqueueCommand { g ->
                val srcSize = image.size
                val destSize = params.destSize ?: srcSize
                val src0 = image.pos
                val dest0 = params.dest
                val src1 = src0 + srcSize
                val dest1 = dest0 + destSize

                g.drawImage(
                    image.data.awtImage,
                    dest0.x.roundToInt(), dest0.y.roundToInt(), dest1.x.roundToInt(), dest1.y.roundToInt(),
                    src0.x.roundToInt(), src0.y.roundToInt(), src1.x.roundToInt(), src1.y.roundToInt(),
                    null)
            }
        }

        override fun paintComponent(g: Graphics) {
            val g2d = g as Graphics2D
            drawCommands.forEach { command -> command(g2d) }
            drawCommands.clear()
        }
    }
}

actual class AppParams(
    val title: String,
    val size: ImmutableVec2,
    actual val assetsRoot: String = "assets"
)
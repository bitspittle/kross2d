package bitspittle.kross2d.engine.app

import bitspittle.kross2d.core.event.Event
import bitspittle.kross2d.core.event.ObservableEvent
import bitspittle.kross2d.core.graphics.ImmutableColor
import bitspittle.kross2d.core.math.ImmutablePt2
import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.core.math.Pt2
import bitspittle.kross2d.engine.graphics.DrawSurface
import bitspittle.kross2d.engine.graphics.DrawSurface.ImageParams
import bitspittle.kross2d.engine.graphics.Screen.Transform
import bitspittle.kross2d.engine.graphics.Screen.Transform.*
import bitspittle.kross2d.engine.graphics.Font
import bitspittle.kross2d.engine.graphics.Image
import bitspittle.kross2d.engine.graphics.Screen
import bitspittle.kross2d.engine.graphics.Screen.Transform.Composite
import bitspittle.kross2d.engine.input.Key
import bitspittle.kross2d.engine.input.Button
import java.awt.*
import java.awt.event.*
import java.awt.geom.AffineTransform
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.WindowConstants
import kotlin.math.roundToInt
import java.awt.Color as AwtColor

fun ImmutableColor.toAwtColor() = AwtColor(r, g, b, a)

fun ImmutableVec2.toDimension() = Dimension(x.roundToInt(), y.roundToInt())

internal actual class ApplicationBackend actual constructor(params: AppParams) {
    private val frame: JFrame
    actual val screen: Screen

    init {
        System.setProperty("sun.java2d.opengl", "true") // For hardware accelerated rendering
        frame = JFrame(params.title)
        frame.defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE // Handle manually
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                quit()
            }
        })

        screen = AwtScreen(params.size)
        frame.contentPane.add(screen)
        frame.isResizable = false
        frame.pack()
        frame.setLocationRelativeTo(null) // This centers the frame on the Window
        // We don't need to navigate around Swing objects; handle TAB, etc. ourselves
        frame.focusTraversalKeysEnabled = false

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

                    KeyEvent.VK_A -> Key.A
                    KeyEvent.VK_B -> Key.B
                    KeyEvent.VK_C -> Key.C
                    KeyEvent.VK_D -> Key.D
                    KeyEvent.VK_E -> Key.E
                    KeyEvent.VK_F -> Key.F
                    KeyEvent.VK_G -> Key.G
                    KeyEvent.VK_H -> Key.H
                    KeyEvent.VK_I -> Key.I
                    KeyEvent.VK_J -> Key.J
                    KeyEvent.VK_K -> Key.K
                    KeyEvent.VK_L -> Key.L
                    KeyEvent.VK_M -> Key.M
                    KeyEvent.VK_N -> Key.N
                    KeyEvent.VK_O -> Key.O
                    KeyEvent.VK_P -> Key.P
                    KeyEvent.VK_Q -> Key.Q
                    KeyEvent.VK_R -> Key.R
                    KeyEvent.VK_S -> Key.S
                    KeyEvent.VK_T -> Key.T
                    KeyEvent.VK_U -> Key.U
                    KeyEvent.VK_V -> Key.V
                    KeyEvent.VK_W -> Key.W
                    KeyEvent.VK_X -> Key.X
                    KeyEvent.VK_Y -> Key.Y
                    KeyEvent.VK_Z -> Key.Z

                    KeyEvent.VK_SPACE -> Key.SPACE
                    KeyEvent.VK_TAB -> Key.TAB
                    KeyEvent.VK_BACK_SPACE -> Key.BACKSPACE
                    KeyEvent.VK_ENTER -> Key.ENTER

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

        val mousePos = Pt2()
        fun updateMousePosAndFireEvent(e: MouseEvent) {
            mousePos.set(e.x, e.y)
            _mouseMoved(mousePos)
        }
        frame.addMouseMotionListener(object : MouseAdapter() {
            override fun mouseMoved(e: MouseEvent) {
                updateMousePosAndFireEvent(e)
            }

            override fun mouseDragged(e: MouseEvent) {
                updateMousePosAndFireEvent(e)
            }
        })
        frame.addMouseListener(object : MouseAdapter() {
            private fun handleAwtButton(awtButton: Int, isDown: Boolean) {
                val button = when(awtButton) {
                    1 -> Button.LEFT
                    2 -> Button.MIDDLE
                    3 -> Button.RIGHT
                    else -> return
                }
                if (isDown) _buttonPressed(button) else _buttonReleased(button)
            }

            override fun mousePressed(e: MouseEvent) {
                handleAwtButton(e.button, true)
            }

            override fun mouseReleased(e: MouseEvent) {
                handleAwtButton(e.button, false)
            }

            override fun mouseDragged(e: MouseEvent) {
                updateMousePosAndFireEvent(e)
            }
        })

        frame.isVisible = true
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

    private class AwtScreen(override val size: ImmutableVec2) : JPanel(), Screen {
        /**
         * Buffer of draw commands. We receive requests to draw to the screen outside of a time we
         * can actually draw them, so we save them up until the next paint happens.
         */
        private val drawCommands = mutableListOf<(Graphics2D) -> Unit>()
        private val restoreTransforms = mutableListOf<AffineTransform>()

        init {
            preferredSize = size.toDimension()
        }

        private fun enqueueCommand(command: (Graphics2D) -> Unit) {
            drawCommands.add(command)
            if (drawCommands.size == 1) {
                repaint() // Schedules this component for repainting
            }
        }

        override fun clear(color: ImmutableColor) {
            enqueueCommand { g ->
                g.background = color.toAwtColor()
                g.clearRect(0, 0, width, height)
            }
        }

        override fun pushTransform(transform: Transform) {
            enqueueCommand { g ->
                restoreTransforms.add(g.transform)
                applyTransform(g, transform)
            }
        }

        override fun popTransform() {
            enqueueCommand { g -> g.transform = restoreTransforms.removeAt(restoreTransforms.lastIndex) }
        }

        private fun applyTransform(g: Graphics2D, transform: Transform) {
            when (transform) {
                is Scale -> g.scale(transform.x, transform.y)
                is Translate -> g.translate(transform.x, transform.y)
                is Composite -> {
                    applyTransform(g, transform.lhs)
                    applyTransform(g, transform.rhs)
                }
            }
        }

        override fun drawLine(pt1: ImmutablePt2, pt2: ImmutablePt2, color: ImmutableColor) {
            enqueueCommand { g ->
                g.color = color.toAwtColor()
                g.drawLine(pt1.x.roundToInt(), pt1.y.roundToInt(), pt2.x.roundToInt(), pt2.y.roundToInt())
            }
        }

        override fun drawImage(image: Image, params: ImageParams) {
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

        override fun measureText(font: Font, text: String): Float {
            val metrics = getFontMetrics(font.fontData.awtFont)
            return metrics.stringWidth(text).toFloat()
        }

        override fun drawText(font: Font, text: String, params: DrawSurface.TextParams) {
            enqueueCommand { g ->
                g.getRenderingHint(RenderingHints.KEY_ANTIALIASING)?.let { hintAntialiasRestore ->
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

                    val awtFont = font.fontData.awtFont
                    val lines = text.split('\n')
                    val startPt = params.toTopLeft(
                        produceWidth = { lines.map { line -> measureText(font, line) }.max() ?: 0f },
                        produceHeight = { lines.size * font.size + (lines.size - 1) * params.spacing })

                    g.font = awtFont
                    g.color = params.color.toAwtColor()
                    var y = startPt.y + awtFont.size2D // drawSting y is underneath text
                    lines.forEach { line ->
                        g.drawString(line, startPt.x, y)
                        y += params.spacing + awtFont.size2D
                    }

                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, hintAntialiasRestore)
                }
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
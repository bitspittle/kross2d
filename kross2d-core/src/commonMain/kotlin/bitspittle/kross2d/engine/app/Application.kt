package bitspittle.kross2d.engine.app

import bitspittle.kross2d.core.event.ObservableEvent
import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.Disposer
import bitspittle.kross2d.core.time.Instant
import bitspittle.kross2d.engine.GameState
import bitspittle.kross2d.engine.assets.AssetLoader
import bitspittle.kross2d.engine.context.DrawContext
import bitspittle.kross2d.engine.context.InitContext
import bitspittle.kross2d.engine.context.UpdateContext
import bitspittle.kross2d.engine.graphics.DrawSurface
import bitspittle.kross2d.engine.graphics.ImmutableDrawSurface
import bitspittle.kross2d.engine.input.DefaultKeyboard
import bitspittle.kross2d.engine.input.Key
import bitspittle.kross2d.engine.input.Keyboard
import bitspittle.kross2d.engine.time.DefaultTimer
import bitspittle.kross2d.engine.time.Timer

/**
 * A subset of the application API that will be exposed to the game logic.
 */
interface ApplicationFacade : Disposable {
    fun quit()
}

/**
 * Parameters for setting up this application.
 *
 * Note: It is up for each platform-specific implementation to declare the fields they need and
 * then read them from the associated [ApplicationBackend]
 */
expect class AppParams {
    val assetsRoot: String
}

/**
 * The game's owning window. When instantiated, it should run forever, blocking the current thread
 * until the user quits.
 *
 * A caller cannot instantiate this directly. Instead, use the helper method [launch].
 */
internal class Application internal constructor(params: AppParams, initialState: GameState) {

    private val backend = ApplicationBackend(params)

    private var currentState = initialState // TODO: Add the ability to change states later

    init {
        val keyboard = DefaultKeyboard()
        backend.keyPressed += { key -> keyboard.handleKey(key, true) }
        backend.keyReleased += { key -> keyboard.handleKey(key, false) }

        val appFacade = object : ApplicationFacade {
            override fun quit() = backend.quit()
        }

        val timer = DefaultTimer()

        val assetLoader = AssetLoader(params.assetsRoot)
        val initContext = object : InitContext {
            override val assetLoader: AssetLoader = assetLoader
            override val screen: ImmutableDrawSurface = backend.drawSurface
            override val timer: Timer = timer
        }

        val updateContext = object : UpdateContext {
            override val app: ApplicationFacade = appFacade
            override val assetLoader: AssetLoader = assetLoader
            override val screen: ImmutableDrawSurface = backend.drawSurface
            override val keyboard: Keyboard = keyboard
            override val timer: Timer = timer
        }

        val drawContext = object : DrawContext {
            override val screen: DrawSurface = backend.drawSurface
            override val timer: Timer = timer
        }

        Disposer.register(appFacade)
        Disposer.register(appFacade, assetLoader)
        Disposer.register(appFacade, currentState)

        currentState.init(initContext)
        var frameStart = Instant.now()
        backend.runForever {
            val lastFrameStart = frameStart
            frameStart = Instant.now()
            timer.lastFrameDuration.setFrom(frameStart - lastFrameStart)

            currentState.update(updateContext)
            keyboard.step()

            currentState.draw(drawContext)
        }

        backend.onQuit {
            Disposer.dispose(appFacade)
            // TODO: Default behavior is to println. If we ever add a DEBUG mode, we should
            //  throw an exception instead if in DEBUG.
            Disposer.freeRemaining()
        }
    }
}

internal expect class ApplicationBackend(params: AppParams) {
    val drawSurface: DrawSurface

    val keyPressed: ObservableEvent<Key>
    val keyReleased: ObservableEvent<Key>

    /**
     * The backend is handed a function which, when called, steps the game loop one frame forward.
     * It should run this in an infinite loop until [quit] is called, at which point it can stop
     * running.
     *
     * It is up to each backend to decide how it wants to play nicely with the OS, e.g. by sleeping
     * between frames to hit a desired FPS.
     */
    fun runForever(frameStep: () -> Unit)

    /**
     * The backend is handed a function which, when called, releases all final game resources.
     * This method should be called just once and only after [quit] is called.
     */
    fun onQuit(quitBlock: () -> Unit)

    /**
     * A request to stop the program. When called, [runForever] should never run again, and the
     * logic passed into [onQuit] should run.
     */
    fun quit()
}

fun launch(appParams: AppParams, initialState: GameState) {
    Application(appParams, initialState)
}
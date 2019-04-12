package bitspittle.kross2d.engine.app

import bitspittle.kross2d.core.event.ObservableEvent
import bitspittle.kross2d.core.memory.Box
import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.Disposer
import bitspittle.kross2d.core.time.Duration
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
    /**
     * Request that next frame, we exit and discard the current state, switching to the new state.
     *
     * If there's a chance you'll want to come back to this state later, consider using [pushState]
     * instead.
     *
     * A good use-case for [changeState] is if you're on a temporary, intermediate state, like a
     * loading screen. When finished, you should enter the new state and discard the loading state
     * as it is no longer needed.
     */
    fun changeState(state: GameState)

    /**
     * Request that next frame, we exit the current state, switching to the new state.
     *
     * A later call to [popState] will re-enter this current state.
     *
     * A good use-case for [pushState] is if you're on the menu screen. After choosing a game
     * option and playing the game for a while, when a user quits, they should be dropped back into
     * the menu screen.
     */
    fun pushState(state: GameState)

    /**
     * Request that next frame, we exit the current state, switching to the state that was active
     * when [pushState] was called.
     */
    fun popState()

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

    private sealed class StateCommand {
        class Change(val gameState: GameState) : StateCommand()
        class Push(val gameState: GameState) : StateCommand()
        object Pop : StateCommand()
    }

    private val backend = ApplicationBackend(params)

    private var stateChangeRequest: StateCommand? = StateCommand.Push(initialState)
    private val stateStack = mutableListOf<Box<GameState>>()

    init {
        val keyboard = DefaultKeyboard()
        backend.keyPressed += { key -> keyboard.handleKey(key, true) }
        backend.keyReleased += { key -> keyboard.handleKey(key, false) }

        val app = Disposer.register(object : ApplicationFacade {
            override fun changeState(state: GameState) {
                stateChangeRequest = StateCommand.Change(state)
            }

            override fun pushState(state: GameState) {
                stateChangeRequest = StateCommand.Push(state)
            }

            override fun popState() {
                if (stateStack.size == 1) {
                    throw IllegalStateException("No more states can be popped. Use app.quit() or app.changeState() instead?")
                }
                stateChangeRequest = StateCommand.Pop
            }

            override fun quit() = backend.quit()
        })

        val timer = DefaultTimer()

        val assetLoader = AssetLoader(params.assetsRoot)
        val initContext = object : InitContext {
            override val assetLoader: AssetLoader = assetLoader
            override val screen: ImmutableDrawSurface = backend.drawSurface
            override val timer: Timer = timer
        }

        val updateContext = object : UpdateContext {
            override val app: ApplicationFacade = app.deref()
            override val assetLoader: AssetLoader = assetLoader
            override val screen: ImmutableDrawSurface = backend.drawSurface
            override val keyboard: Keyboard = keyboard
            override val timer: Timer = timer
        }

        val drawContext = object : DrawContext {
            override val screen: DrawSurface = backend.drawSurface
            override val timer: Timer = timer
        }

        // Following vars are set immediately when the initialState is pushed on the stack
        lateinit var frameStart: Instant
        lateinit var currentState: GameState

        backend.runForever {
            stateChangeRequest?.let { stateCommand ->
                stateStack.lastOrNull()?.let { Disposer.dispose(it) }
                when (stateCommand) {
                    is StateCommand.Change -> {
                        stateStack.removeAt(stateStack.lastIndex)
                        stateStack.add(Disposer.register(app, stateCommand.gameState))
                    }
                    is StateCommand.Push -> {
                        stateStack.add(Disposer.register(app, stateCommand.gameState))
                    }
                    is StateCommand.Pop -> {
                        stateStack.removeAt(stateStack.lastIndex)
                    }
                }
                this.stateChangeRequest = null

                stateStack.last().let { stateToEnter ->
                    frameStart = Instant.now()
                    timer.lastFrameDuration.setFrom(Duration.ZERO)
                    assetLoader.disposableContext = stateToEnter
                    currentState = stateToEnter.deref().also { it.init(initContext) }
                }
            }

            val lastFrameStart = frameStart
            frameStart = Instant.now()
            timer.lastFrameDuration.setFrom(frameStart - lastFrameStart)

            currentState.update(updateContext)
            keyboard.step()

            currentState.draw(drawContext)
        }

        backend.onQuit {
            Disposer.dispose(app)
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
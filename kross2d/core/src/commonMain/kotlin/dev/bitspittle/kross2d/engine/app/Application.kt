package dev.bitspittle.kross2d.engine.app

import dev.bitspittle.kross2d.core.event.ObservableEvent
import dev.bitspittle.kross2d.core.math.ImmutablePt2
import dev.bitspittle.kross2d.core.memory.Disposer
import dev.bitspittle.kross2d.core.memory.disposableOf
import dev.bitspittle.kross2d.core.memory.register
import dev.bitspittle.kross2d.core.memory.registerEmpty
import dev.bitspittle.kross2d.core.time.Duration
import dev.bitspittle.kross2d.core.time.Instant
import dev.bitspittle.kross2d.engine.GameState
import dev.bitspittle.kross2d.engine.assets.AssetLoader
import dev.bitspittle.kross2d.engine.context.DrawContext
import dev.bitspittle.kross2d.engine.context.EnterContext
import dev.bitspittle.kross2d.engine.context.UpdateContext
import dev.bitspittle.kross2d.engine.graphics.ImmutableScreen
import dev.bitspittle.kross2d.engine.graphics.Screen
import dev.bitspittle.kross2d.engine.input.*
import dev.bitspittle.kross2d.engine.input.DefaultKeyboard
import dev.bitspittle.kross2d.engine.input.DefaultMouse
import dev.bitspittle.kross2d.engine.memory.Scopes
import dev.bitspittle.kross2d.engine.time.DefaultTimer
import dev.bitspittle.kross2d.engine.time.Timer

/**
 * A subset of the application API that will be exposed to the game logic.
 */
interface ApplicationFacade {
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
    private val stateStack = mutableListOf<GameState>()

    init {
        val keyboard = DefaultKeyboard()
        backend.keyPressed += { key -> keyboard.handleKey(key, true) }
        backend.keyReleased += { key -> keyboard.handleKey(key, false) }

        val mouse = DefaultMouse()
        backend.mouseMoved += { pos -> mouse.pos.set(pos) }
        backend.buttonPressed += { button -> mouse.handleButton(button, true) }
        backend.buttonReleased += { button -> mouse.handleButton(button, false) }

        val scopes = object : Scopes {
            override val app = Disposer.registerEmpty()
            override var currState = Disposer.registerEmpty(app)
        }

        val app = object : ApplicationFacade {
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
        }
        val timer = DefaultTimer()

        val assetLoader = AssetLoader(params.assetsRoot, scopes)
        val initContext = object : EnterContext {
            override val assetLoader: AssetLoader = assetLoader
            override val screen: ImmutableScreen = backend.screen
            override val timer: Timer = timer
            override val scopes: Scopes = scopes
        }

        val updateContext = object : UpdateContext {
            override val app: ApplicationFacade = app
            override val assetLoader: AssetLoader = assetLoader
            override val screen: ImmutableScreen = backend.screen
            override val keyboard: Keyboard = keyboard
            override val mouse: Mouse = mouse
            override val timer: Timer = timer
            override val scopes: Scopes = scopes
        }

        val drawContext = object : DrawContext {
            override val screen: Screen = backend.screen
            override val timer: Timer = timer
        }

        // Following vars are set immediately when the initialState is pushed on the stack
        lateinit var frameStart: Instant
        lateinit var currentState: GameState

        backend.runForever {
            stateChangeRequest?.let { stateCommand ->
                when (stateCommand) {
                    is StateCommand.Change -> {
                        stateStack.removeAt(stateStack.lastIndex)
                        stateStack.add(stateCommand.gameState)
                    }
                    is StateCommand.Push -> {
                        stateStack.add(stateCommand.gameState)
                    }
                    is StateCommand.Pop -> {
                        stateStack.removeAt(stateStack.lastIndex)
                    }
                }
                this.stateChangeRequest = null

                stateStack.last().let { stateToEnter ->
                    frameStart = Instant.now()
                    timer.lastFrame.setFrom(Duration.Zero)
                    Disposer.dispose(scopes.currState)
                    scopes.currState = Disposer.registerEmpty()
                    currentState = stateToEnter
                    stateToEnter.enter(initContext)
                }
            }

            val lastFrameStart = frameStart
            frameStart = Instant.now()
            timer.lastFrame.setFrom(frameStart - lastFrameStart)

            currentState.update(updateContext)
            keyboard.step()
            mouse.step()

            currentState.draw(drawContext)
        }

        backend.onQuit {
            Disposer.dispose(scopes.currState)
            Disposer.dispose(scopes.app)
            // TODO: Default behavior is to println. If we ever add a DEBUG mode, we should
            //  throw an exception instead if in DEBUG.
            Disposer.freeRemaining()
        }
    }
}

internal expect class ApplicationBackend(params: AppParams) {
    val screen: Screen

    val keyPressed: ObservableEvent<Key>
    val keyReleased: ObservableEvent<Key>

    val mouseMoved: ObservableEvent<ImmutablePt2>
    val buttonPressed: ObservableEvent<Button>
    val buttonReleased: ObservableEvent<Button>

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
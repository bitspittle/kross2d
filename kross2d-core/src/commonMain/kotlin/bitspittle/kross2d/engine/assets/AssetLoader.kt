package bitspittle.kross2d.engine.assets

import bitspittle.kross2d.core.event.Event
import bitspittle.kross2d.core.event.ObservableEvent
import bitspittle.kross2d.core.memory.*
import bitspittle.kross2d.engine.GameState
import bitspittle.kross2d.engine.app.Application
import bitspittle.kross2d.engine.audio.Sound
import bitspittle.kross2d.engine.graphics.Font
import bitspittle.kross2d.engine.graphics.Image

/**
 * A handle to an asset that will get loaded asynchronously.
 *
 * If [value] is non-null, this particular asset loaded successfully. Otherwise, the caller may
 * want to check [state] to see if any asset failed to load, which could be particularly useful for
 * logging errors / aborting the game.
 */
class Asset<T: Disposable>(parent: Box<Disposable>, val path: String) : Disposable {
    init {
        Disposer.register(parent, this)
    }

    enum class State {
        LOADING,
        LOADED,
        FAILED,
        DISPOSED,
    }

    var state = State.LOADING
        private set(value) {
            field = value
            if (field == State.LOADED) {
                fireOnLoaded(this.value!!)
            }
            _onLoaded.clear()
        }

    private val _onLoaded = Event<Box<T>> { value?.let { fireOnLoaded(it) } }
    val onLoaded: ObservableEvent<Box<T>> = _onLoaded

    /**
     * The value of this handle. Will only be non-null if the current [state] is [State.LOADED]
     */
    var value: Box<T>? = null
        private set

    /**
     * Convenience function, allowing a caller to shorten the common expression
     * `asset.value?.deref { x -> ... }` to `asset.ifLoaded { x -> ... }`
     *
     * This will not run [block] if the asset is still loading; however, if the asset was already
     * loaded and then disposed, this will throw an exception as if the caller had used the
     * disposed asset directly.
     */
    inline fun <R> ifLoaded(block: (T) -> R): R? {
        return value?.deref(block)
    }

    internal fun setValue(value: T?) {
        if (state == State.DISPOSED) {
            // It's possible that this asset shell already got disposed by the time we finished
            // loading the underlying data. This probably won't happen in practice, but just in
            // case, let's handle it by consuming the data.
            value?.use {}
            return
        }

        assertLoading()
        if (value != null) {
            this.value = Disposer.register(this, value)
            state = State.LOADED
        }
        else {
            state = State.FAILED
        }
    }

    private fun fireOnLoaded(value: Box<T>) {
        _onLoaded(value)
        _onLoaded.clear()
    }

    internal fun notifyFailure() {
        if (state == State.DISPOSED) {
            // We got disposed before the target value had a chance to fail loading. This probably
            // won't happen in practice, but if it does, who cares! Let's just ignore it.
            return
        }
        assertLoading()
        state = State.FAILED
    }

    private fun assertLoading() {
        if (state != State.LOADING) {
            throw IllegalStateException("Attempting to modify frozen AssetHandle")
        }
    }

    override fun dispose() {
        state = State.DISPOSED
    }
}

/**
 * Class which handles the loading (and caching) of game assets.
 *
 * Note that assets are [Disposable] and are furthermore associated with the current [GameState],
 * so when you exit a game state, all assets are deallocated.
 *
 * If you want an asset to live longer than the current game state, you can re-register its parent
 * manually. The application itself is a disposable, so you could write something like this:
 *
 * ```
 *   // init
 *   val asset = ctx.assetLoader.loadImage("cursor.png").also { Disposer.register(ctx.app, it) }
 * ```
 *
 * Another approach could be to indirectly transfer all assets to the next game state you're
 * entering via [Disposer]:
 *
 * ```
 *   class GameAssets(
 *     val player: Asset<Image>,
 *     val background: Asset<Image>,
 *     ...
 *   )
 *
 *   class LoadingState : GameState() {
 *     private lateinit var gameAssets: GameAssets
 *
 *     // init
 *     gameAssets = GameAssets(
 *       player = ctx.assetLoader.loadImage("player.png"),
 *       background = ctx.assetLoader.loadImage("background.png")
 *       ...
 *     )
 *
 *     // update
 *     if (ctx.assetLoader.allAssets.all { it.state == Asset.State.LOADED }) {
 *        val nextState = MainState(gameAssets)
 *        // Make sure assets don't get released when we leave this state
 *        Disposer.transferChildren(from = this, to = nextState)
 *        ctx.app.changeState(nextState)
 *     }
 *   }
 */
class AssetLoader(root: String) {
    /**
     * A context which any new, loaded assets will be registered against as the owning disposable.
     *
     * Dev note: The [Application] class should set this to the current [GameState] before the user
     * ever gets a chance to load assets.
     */
    internal lateinit var disposableContext: Box<Disposable>

    private val backend = AssetLoaderBackend(root)

    private val cachedFonts = mutableMapOf<String, Asset<Font>>()
    private val cachedImages = mutableMapOf<String, Asset<Image>>()
    private val cachedSounds = mutableMapOf<String, Asset<Sound>>()

    fun loadFont(relativePath: String): Asset<Font> {
        return cachedFonts.getOrPut(relativePath) {
            Asset<Font>(disposableContext, relativePath).apply {
                backend.loadFontInto(this)
                cachedFonts[relativePath] = this
                Disposer.register(this, disposable { cachedFonts.remove(relativePath) })
            }
        }
    }

    fun loadImage(relativePath: String): Asset<Image> {
        return cachedImages.getOrPut(relativePath) {
            Asset<Image>(disposableContext, relativePath).apply {
                backend.loadImageInto(this)
                cachedImages[relativePath] = this
                Disposer.register(this, disposable { cachedImages.remove(relativePath) })
            }
        }
    }

    fun loadSound(relativePath: String): Asset<Sound> {
        return cachedSounds.getOrPut(relativePath) {
            Asset<Sound>(disposableContext, relativePath).apply {
                backend.loadSoundInto(this)
                cachedSounds[relativePath] = this
                Disposer.register(this, disposable { cachedSounds.remove(relativePath) })
            }
        }
    }
}

expect class AssetLoaderBackend(root: String) {
    fun loadFontInto(asset: Asset<Font>)
    fun loadImageInto(asset: Asset<Image>)
    fun loadSoundInto(asset: Asset<Sound>)
}



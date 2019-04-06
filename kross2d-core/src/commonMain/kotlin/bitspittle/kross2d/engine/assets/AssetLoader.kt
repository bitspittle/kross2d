package bitspittle.kross2d.engine.assets

import bitspittle.kross2d.core.event.Event
import bitspittle.kross2d.core.event.ObservableEvent
import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.Disposer
import bitspittle.kross2d.core.memory.disposable
import bitspittle.kross2d.engine.audio.Sound
import bitspittle.kross2d.engine.graphics.Image
import bitspittle.kross2d.engine.GameState

/**
 * A handle to an asset that will get loaded asynchronously.
 *
 * If [value] is non-null, this particular asset loaded successfully. Otherwise, the caller may
 * want to check [state] to see if any asset failed to load, which could be particularly useful for
 * logging errors / aborting the game.
 */
class Asset<T: Disposable>(parent: Disposable, val path: String) : Disposable {
    init {
        Disposer.register(parent, this)
    }

    enum class State {
        LOADING,
        SUCCEEDED,
        FAILED,
        DISPOSED,
    }

    var state = State.LOADING
        private set(value) {
            field = value
            if (field == State.SUCCEEDED) {
                fireOnLoaded(this.value!!)
            }
            _onLoaded.clear()
        }

    private val _onLoaded = Event<T> { value?.let { fireOnLoaded(it) } }
    val onLoaded: ObservableEvent<T> = _onLoaded

    /**
     * The value of this handle. Will only be non-null if the current [state] is [State.SUCCEEDED]
     */
    var value: T? = null
        private set

    internal fun setValue(value: T?) {
        if (state == State.DISPOSED) {
            if (value != null) {
                // We got disposed before the target value ever finished loading. This probably won't
                // happen in practice, but just in case, let's handle it by disposing the asset value
                // as well.
                Disposer.register(value)
                Disposer.dispose(value)
            }
            return
        }

        assertLoading()
        if (value != null) {
            Disposer.register(this, value)
            this.value = value
            state = State.SUCCEEDED
        }
        else {
            state = State.FAILED
        }
    }

    private fun fireOnLoaded(value: T) {
        _onLoaded(value)
        _onLoaded.clear()
    }

    internal fun notifyFailure() {
        if (state == State.DISPOSED) {
            // We got disposed before the target value had a chance to fail loading. This probably
            // won't happen in practice, but just in case, let's handle this by ignoring it.
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
 * An immutable interface for querying all loaded assets.
 */
interface Assets {
    val allImages: List<Image>
    val allSounds: List<Sound>
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
 */
class AssetLoader(root: String): Assets {
    /**
     * A context which any new, loaded assets will be registered against. Application logic should
     * set this to the current [GameState] before the user ever gets a chance to load assets.
     */
    internal lateinit var disposableContext: Disposable

    private val backend = AssetLoaderBackend(root)

    private val cachedImages = mutableMapOf<String, Asset<Image>>()
    private val cachedSounds = mutableMapOf<String, Asset<Sound>>()

    override val allImages
        get() = cachedImages.values.mapNotNull { it.value }

    override val allSounds
        get() = cachedSounds.values.mapNotNull { it.value }

    fun loadImage(relativePath: String): Asset<Image> {
        return cachedImages.getOrPut(relativePath) {
            Asset<Image>(disposableContext, relativePath).apply {
                cachedImages[relativePath] = this
                backend.loadImageInto(this)
                Disposer.register(this, disposable { cachedImages.remove(relativePath) })
            }
        }
    }

    fun loadSound(relativePath: String): Asset<Sound> {
        return cachedSounds.getOrPut(relativePath) {
            Asset<Sound>(disposableContext, relativePath).apply {
                cachedSounds[relativePath] = this
                backend.loadSoundInto(this)
                Disposer.register(this, disposable { cachedSounds.remove(relativePath) })
            }
        }
    }
}

expect class AssetLoaderBackend(root: String) {
    fun loadImageInto(asset: Asset<Image>)
    fun loadSoundInto(asset: Asset<Sound>)
}



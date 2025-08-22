package dev.bitspittle.kross2d.engine.assets

import dev.bitspittle.kross2d.core.event.Event
import dev.bitspittle.kross2d.core.event.ObservableEvent
import dev.bitspittle.kross2d.core.memory.*
import dev.bitspittle.kross2d.engine.GameState
import dev.bitspittle.kross2d.engine.audio.Music
import dev.bitspittle.kross2d.engine.audio.Sound
import dev.bitspittle.kross2d.engine.graphics.Font
import dev.bitspittle.kross2d.engine.graphics.Image
import dev.bitspittle.kross2d.engine.memory.Scopes

/**
 * A handle to an asset that will get loaded asynchronously.
 *
 * If [data] is non-null, this particular asset loaded successfully. Otherwise, the caller may
 * want to check [state] to see if any asset failed to load, which could be particularly useful for
 * logging errors / aborting the game.
 */
class Asset<D: Disposable>(parent: ImmutableDisposable, val path: String) : Disposable(parent) {
    enum class State {
        LOADING,
        LOADED,
        FAILED,
    }

    var state = State.LOADING
        private set(value) {
            field = value
            if (field == State.LOADED) {
                fireOnLoaded(this.data!!)
            }
            _onLoaded.clear()
        }

    private val _onLoaded = Event<D> { data?.let { fireOnLoaded(it) } }
    val onLoaded: ObservableEvent<D> = _onLoaded

    /**
     * The value of this handle. Will only be non-null if the current [state] is [State.LOADED]
     */
    var data: D? = null
        private set

    internal fun setData(data: D?) {
        if (this.disposed) {
            // It's possible that this asset shell already got disposed by the time we finished
            // loading the underlying data. This probably won't happen in practice, but just in
            // case, let's handle it by consuming the data.
            data?.use {}
            return
        }

        assertLoading()
        if (data != null) {
            Disposer.reparent(this, data)
            this.data = data
            state = State.LOADED
        }
        else {
            state = State.FAILED
        }
    }

    private fun fireOnLoaded(data: D) {
        _onLoaded(data)
        _onLoaded.clear()
    }

    internal fun notifyFailure() {
        if (this.disposed) {
            // We got disposed before the target data had a chance to fail loading. This probably
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
 *   // enter
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
 *     // enter
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
class AssetLoader(root: String, private val scopes: Scopes) {
    private val backend = AssetLoaderBackend(root)

    private val cachedFonts = mutableMapOf<String, Asset<Font>>()
    private val cachedImages = mutableMapOf<String, Asset<Image>>()
    private val cachedSounds = mutableMapOf<String, Asset<Sound>>()
    private val cachedMusic = mutableMapOf<String, Asset<Music>>()

    fun loadFont(relativePath: String, scope: ImmutableDisposable = scopes.currState): Asset<Font> {
        return cachedFonts.getOrPut(relativePath) {
            Asset<Font>(scope, relativePath).apply {
                backend.loadFontInto(this)
                cachedFonts[relativePath] = this
                disposable(this) { cachedFonts.remove(relativePath) }
            }
        }
    }

    fun loadImage(relativePath: String, scope: ImmutableDisposable = scopes.currState): Asset<Image> {
        return cachedImages.getOrPut(relativePath) {
            Asset<Image>(scope, relativePath).apply {
                backend.loadImageInto(this)
                cachedImages[relativePath] = this
                disposable(this) { cachedImages.remove(relativePath) }
            }
        }
    }

    fun loadSound(relativePath: String, scope: ImmutableDisposable = scopes.currState): Asset<Sound> {
        return cachedSounds.getOrPut(relativePath) {
            Asset<Sound>(scope, relativePath).apply {
                backend.loadSoundInto(this)
                cachedSounds[relativePath] = this
                disposable(this) { cachedSounds.remove(relativePath) }
            }
        }
    }

    fun loadMusic(relativePath: String, scope: ImmutableDisposable = scopes.currState): Asset<Music> {
        return cachedMusic.getOrPut(relativePath) {
            Asset<Music>(scope, relativePath).apply {
                backend.loadMusicInto(this)
                cachedMusic[relativePath] = this
                disposable(this) { cachedMusic.remove(relativePath) }
            }
        }
    }
}

expect class AssetLoaderBackend(root: String) {
    fun loadFontInto(asset: Asset<Font>)
    fun loadImageInto(asset: Asset<Image>)
    fun loadSoundInto(asset: Asset<Sound>)
    fun loadMusicInto(asset: Asset<Music>)
}



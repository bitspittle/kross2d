package dev.bitspittle.kross2d.engine.assets

import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.Disposer
import dev.bitspittle.kross2d.core.memory.register
import dev.bitspittle.kross2d.engine.GameState
import dev.bitspittle.kross2d.engine.audio.Music
import dev.bitspittle.kross2d.engine.audio.Sound
import dev.bitspittle.kross2d.engine.graphics.Font
import dev.bitspittle.kross2d.engine.graphics.Image
import dev.bitspittle.kross2d.engine.memory.Scopes

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

    fun loadFont(relativePath: String, scope: Disposable = scopes.currState): Asset<Font> {
        return cachedFonts.getOrPut(relativePath) {
            Asset<Font>(scope, relativePath).apply {
                backend.loadFontInto(this)
                cachedFonts[relativePath] = this
                Disposer.register(this) { cachedFonts.remove(relativePath) }
            }
        }
    }

    fun loadImage(relativePath: String, scope: Disposable = scopes.currState): Asset<Image> {
        return cachedImages.getOrPut(relativePath) {
            Asset<Image>(scope, relativePath).apply {
                backend.loadImageInto(this)
                cachedImages[relativePath] = this
                Disposer.register(this) { cachedImages.remove(relativePath) }
            }
        }
    }

    fun loadSound(relativePath: String, scope: Disposable = scopes.currState): Asset<Sound> {
        return cachedSounds.getOrPut(relativePath) {
            Asset<Sound>(scope, relativePath).apply {
                backend.loadSoundInto(this)
                cachedSounds[relativePath] = this
                Disposer.register(this) { cachedSounds.remove(relativePath) }
            }
        }
    }

    fun loadMusic(relativePath: String, scope: Disposable = scopes.currState): Asset<Music> {
        return cachedMusic.getOrPut(relativePath) {
            Asset<Music>(scope, relativePath).apply {
                backend.loadMusicInto(this)
                cachedMusic[relativePath] = this
                Disposer.register(this) { cachedMusic.remove(relativePath) }
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

package bitspittle.kross2d.engine.assets

import bitspittle.kross2d.core.event.Event
import bitspittle.kross2d.core.event.ObservableEvent
import bitspittle.kross2d.engine.audio.Sound
import bitspittle.kross2d.engine.graphics.Image

/**
 * A handle to an asset that will get loaded asynchronously.
 *
 * If [value] is non-null, this particular asset loaded successfully. Otherwise, the caller may
 * want to check [state] to see if any asset failed to load, which could be particularly useful for
 * logging errors / aborting the game.
 */
class Asset<T>(val path: String) {
    enum class State {
        LOADING,
        SUCCEEDED,
        FAILED
    }

    var state = State.LOADING
        private set

    private val _onLoaded = Event<T> { value?.let { fireOnLoaded(it) } }
    val onLoaded: ObservableEvent<T> = _onLoaded

    /**
     * The value of this handle. Will only be non-null if the current [state] is [State.SUCCEEDED]
     */
    var value: T? = null
        private set

    internal fun setValue(value: T?) {
        assertLoading()

        if (value != null) {
            state = State.SUCCEEDED
            fireOnLoaded(value)
            this.value = value
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
        assertLoading()
        state = State.FAILED
    }

    private fun assertLoading() {
        if (state != State.LOADING) {
            throw IllegalStateException("Attempting to modify frozen AssetHandle")
        }
    }
}

class AssetLoader(root: String) {
    private val backend = AssetLoaderBackend(root)

    fun loadImage(relativePath: String): Asset<Image> {
        return Asset<Image>(relativePath).apply { backend.loadImageInto(this) }
    }

    fun loadSound(relativePath: String): Asset<Sound> {
        return Asset<Sound>(relativePath).apply { backend.loadSoundInto(this) }
    }
}

expect class AssetLoaderBackend(root: String) {
    fun loadImageInto(asset: Asset<Image>)
    fun loadSoundInto(asset: Asset<Sound>)
}



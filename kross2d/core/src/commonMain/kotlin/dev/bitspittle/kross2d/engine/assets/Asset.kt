package dev.bitspittle.kross2d.engine.assets

import dev.bitspittle.kross2d.core.event.Event
import dev.bitspittle.kross2d.core.event.EventEmitter
import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.Disposer
import dev.bitspittle.kross2d.core.memory.use

/**
 * A handle to an asset that will get loaded asynchronously.
 *
 * If [data] is non-null, this particular asset loaded successfully. Otherwise, the caller may
 * want to check [state] to see if any asset failed to load, which could be particularly useful for
 * logging errors / aborting the game.
 */
class Asset<D: Disposable>(parent: Disposable, val path: String) : Disposable() {
    init {
        Disposer.register(parent, this, Disposer.AlreadyRegisteredStrategy.REREGISTER)
    }

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
            _loaded.clear()
        }

    private val _loaded = EventEmitter<D> { data?.let { fireOnLoaded(it) } }
    val loaded: Event<D> = _loaded

    /**
     * The value of this handle. Will only be non-null if the current [state] is [State.LOADED]
     */
    var data: D? = null
        private set

    internal fun setData(data: D?) {
        if (this.isDisposed) {
            // It's possible that this asset shell already got disposed by the time we finished
            // loading the underlying data. This probably won't happen in practice, but just in
            // case, let's handle it by consuming the data.
            data?.use {}
            return
        }

        assertLoading()
        if (data != null) {
            Disposer.setParent(this, data)
            this.data = data
            state = State.LOADED
        }
        else {
            state = State.FAILED
        }
    }

    private fun fireOnLoaded(data: D) {
        _loaded(data)
        _loaded.clear()
    }

    internal fun notifyFailure() {
        if (this.isDisposed) {
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

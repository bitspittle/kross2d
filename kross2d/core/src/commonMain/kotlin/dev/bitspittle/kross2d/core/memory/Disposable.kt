package dev.bitspittle.kross2d.core.memory

class DisposableException(msg: String) : Exception(msg)

/**
 * Base class for subclasses that either themselves contain heavy resources (that should be
 * released as soon as possible once the object is no longer in use), OR they're expected to be
 * the parents of such objects.
 *
 * Disposable objects should be registered using [Disposer.register] and cleaned up by calling
 * [Disposer.dispose], if necessary (often, you can rely on a parent disposable being cleaned up
 * for you).
 *
 * In *Kross2D*, every `GameState` as well as the parent application (accessible via `ctx.app`
 * depending on the context) are disposable containers, so if you register your disposable
 * against them, they will be automatically cleaned up when those objects go out of scope.
 */
abstract class Disposable  {
    var disposed: Boolean = false
        private set

    // This should ONLY be called by [Disposer.dispose]
    internal fun dispose() {
        disposed = true
        onDisposed()
    }

    /**
     * Release any resources held by this object.
     *
     * WARNING: You should NOT call this method directly; rather, call [Disposer.dispose] instead.
     * This way, you are guaranteed that this method will only ever be called once, and also any
     * children disposables will also be cleaned up.
     */
    protected open fun onDisposed() {}
}

/**
 * Convenience function for creating an anonymous disposable.
 *
 * Once created, it still needs to be explicitly registered via [Disposer.register].
 */
inline fun disposableOf(crossinline block: () -> Unit = {}) = object : Disposable() {
    override fun onDisposed() {
        block()
    }
}

package dev.bitspittle.kross2d.core.memory

import dev.bitspittle.kross2d.core.event.Event

class DisposableException(msg: String) : Exception(msg)

/**
 * Base class for subclasses that either themselves contain heavy resources (that should be
 * released as soon as possible once the object is no longer in use), OR they're expected to be
 * the parents of such objects.
 *
 * Disposable objects should be registered using [Disposer.register] and cleaned up by calling
 * [Disposer.dispose], if necessary (often, you can rely on a parent disposable being cleaned up
 * for you). If [autoRegister] is passed in with true, then [Disposer.register] will be called
 * automatically.
 *
 * In *Kross2D*, every `GameState` as well as the parent application (accessible via `ctx.app`
 * depending on the context) are disposable containers, so if you register your disposable
 * against them, they will be automatically cleaned up when those objects go out of scope.
 *
 * @param autoRegister Set true to automatically call [Disposer.register] on creation of this
 *   disposable. You are encouraged to change this disposable's parent after registering using
 *   [Disposer.setParent].
 */
abstract class Disposable(autoRegister: Boolean = true) {
    init {
        if (autoRegister) Disposer.register(this)
    }

    var isDisposed: Boolean = false
        private set

    val disposed: Event<Disposable> get() = Disposer.disposed(this)

    // This should ONLY be called by [Disposer.dispose]
    internal fun dispose() {
        isDisposed = true
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
inline fun disposableOf(crossinline block: () -> Unit = {}) = object : Disposable(autoRegister = false) {
    override fun onDisposed() {
        block()
    }
}

package bitspittle.kross2d.core.memory

import kotlin.jvm.Synchronized

class AlreadyDisposedException(msg: String) : Exception(msg)

/**
 * Read-only handle to a [Disposable]. You can use this handle to check if the value is already
 * disposed as well as set it as the parent of another [Disposable], but otherwise you can't
 * interact with it (e.g. to delete it using [Disposer]).
 */
interface ImmutableDisposable {
    val disposed: Boolean
}

/**
 * Interface to mark classes that either themselves contain heavy resources (that should be
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
abstract class Disposable(parent: ImmutableDisposable? = null) : ImmutableDisposable {
    init {
        if (parent == null) Disposer.register(this) else Disposer.register(parent, this)
    }

    private var _disposed = false
    final override val disposed: Boolean
        get() = _disposed

    internal fun dispose() {
        _disposed = true
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
 * Convenience function for calling [Disposer.reparent] indirectly, in a chain-friendly way
 *
 * Compare:
 *
 * ```
 * val child = SomeDisposable()
 * Disposer.reparent(parent, child)
 * ```
 *
 * to
 *
 * ```
 * val child = SomeDisposable().setParent(parent)
 * ```
 */
fun <D: Disposable> D.setParent(parent: ImmutableDisposable): D {
    Disposer.reparent(parent, this)
    return this
}

/**
 * Convenience method for scoping a disposable to a block. After the block is finished, the
 * disposable will be disposed.
 */
inline fun <D: Disposable> D.use(block: (D) -> Unit) {
    if (this.disposed) {
        throw AlreadyDisposedException("Can't use disposed Disposable")
    }
    try {
        block(this)
    }
    finally {
        Disposer.dispose(this)
    }
}

/**
 * Convenience function for creating an anonymous disposable
 */
inline fun disposable(crossinline block: () -> Unit): Disposable {
    return object : Disposable() {
        override fun onDisposed() {
            block()
        }
    }
}

/**
 * Convenience function for creating a parented anonymous disposable
 */
inline fun disposable(parent: ImmutableDisposable, crossinline block: () -> Unit): Disposable {
    return object : Disposable(parent) {
        override fun onDisposed() {
            block()
        }
    }
}

/**
 * A global object useful for explicitly tracking and releasing [Disposable] objects, when waiting
 * on the GC to lazily run isn't viable.
 *
 * When you instantiate a [Disposable] (or subclass), it automatically gets registered with
 * this global disposer, and you really should call [Disposer.dispose] on it at some point.
 *
 * You should also call [Disposer.freeRemaining] right before you exit your app, as that will
 * report if any disposables were not explicitly freed (meaning you have a memory leak!)
 *
 * Disposables can (and should!) be parented hierarchically, via [Disposer.reparent] (or
 * the convenience function [Disposable.setParent]:
 *
 * ```
 * val app = App()
 * val project1 = Project().setParent(app) // Or Disposer.reparent(app, project1)
 * val project2 = Project().setParent(app)
 * val resource = Resource().setParent(project1)
 * ```
 *
 * To release a chain of disposables, just call [Disposer.dispose]:
 *
 * ```
 * Disposer.dispose(app)
 * assert(project1.disposed == true)
 * assert(project2.disposed == true)
 * assert(resource.disposed == true)
 * ```
 */
object Disposer {
    class IllegalDisposerOperation(msg: String) : Exception(msg)

    private val roots = mutableListOf<Disposable>()

    /** Map of a Disposable to any children registered with it **/
    private val childrenOf = mutableMapOf<ImmutableDisposable, MutableList<Disposable>>()

    /** Map of a Disposable to its parent **/
    private val parentOf = mutableMapOf<Disposable, ImmutableDisposable>()

    /**
     * Register a top-level disposable.
     *
     * Usually, it's better to register a disposable underneath a parent, so you can chain their
     * release at the same time. You can still call [reparent] later if necessary.
     */
    @Synchronized
    internal fun <D : Disposable> register(disposable: D) {
        verifyNewDisposable(disposable)
        roots.add(disposable)
    }

    /**
     * Register a disposable underneath a parent. When a caller requests for the parent to be
     * disposed, its children will all be disposed first (and so-on).
     *
     * Note that calling this method on a disposable that's already been registered will reparent
     * it (as long as doing so doesn't cause an infinite cycle, e.g. setting your kid as your
     * parent)
     */
    @Synchronized
    internal fun <D1 : ImmutableDisposable, D2 : Disposable> register(parent: D1, child: D2) {
        verifyNewDisposable(child)
        reparent(parent, child)
    }

    private fun <D : Disposable> verifyNewDisposable(disposable: D) {
        if (disposable.disposed) {
            throw AlreadyDisposedException("Can't register a pre-disposed disposable")
        }

        if (parentOf[disposable] != null || roots.contains(disposable)) {
            throw IllegalDisposerOperation("A disposable can only be registered once")
        }
    }

    /**
     * Move a disposable to a new parent.
     */
    @Synchronized
    fun <D1 : ImmutableDisposable, D2 : Disposable> reparent(newParent: D1, child: D2) {
        if (newParent.disposed) {
            throw AlreadyDisposedException("Tried to reparent onto a disposable that has already been disposed")
        }
        if (child.disposed) {
            throw AlreadyDisposedException("Tried to reparent a disposable that has already been disposed")
        }

        // You are allowed to change the parent, but no paradoxes allowed!
        if (newParent.isSelfOrDescendantOf(child)) {
            throw IllegalDisposerOperation("Can't reparent a disposable underneath itself.")
        }

        parentOf[child]?.let { oldParent ->
            if (oldParent === newParent) {
                return // The parent didn't change, so this is a no-op request
            }
        }

        removeFromSiblings(child)

        parentOf[child] = newParent
        childrenOf.getOrPut(newParent) { mutableListOf() }.add(child)
    }


    /**
     * Move all children from one owner to another.
     *
     * This will fail if you try to move children to a new parent that, itself, is
     * owned by the first one.
     */
    @Synchronized
    fun transferChildren(from: ImmutableDisposable, to: ImmutableDisposable) {
        if (from === to) {
            return
        }

        if (to.isDescendantOf(from)) {
            throw IllegalDisposerOperation("Can't transfer to a new parent owned by the existing parent.")
        }

        val newChildren = childrenOf.getOrPut(to) { mutableListOf() }
        childrenOf[from]?.let { newChildren.addAll(it) }
        childrenOf.remove(from)
        newChildren.forEach { child -> parentOf[child] = to }
    }

    /**
     * Release the resources held by this boxed [Disposable], as well as all its children.
     *
     * It is an error to dispose an already-disposed box.
     *
     * Children will be cleaned up before the parent is.
     */
    @Synchronized
    fun dispose(disposable: Disposable) {
        if (disposable.disposed) {
            throw AlreadyDisposedException("Tried to dispose a box that has already been disposed")
        }

        handleDisposeChildren(disposable)
        removeFromSiblings(disposable)
        handleDispose(disposable)
    }

    /**
     * A safe version of [dispose] that won't throw an exception if the target disposable is
     * already disposed.
     */
    @Synchronized
    fun tryDispose(disposable: Disposable): Boolean {
        if (!disposable.disposed) {
            dispose(disposable)
            return true
        }
        return false
    }

    private fun removeFromSiblings(child: Disposable) {
        parentOf.remove(child)?.let { parent ->
            childrenOf[parent]!!.let { siblings ->
                siblings.remove(child)
                if (siblings.isEmpty()) {
                    childrenOf.remove(parent)
                }
            }
        } ?: roots.remove(child)
    }

    /**
     * Free any remaining [Disposable]s. Ideally, all disposables should be released via [dispose]
     * calls already, but it's a good idea to run this before exiting your program.
     *
     * If any disposables are freed by this method, an error string will be sent to the passed-in
     * [notify] callback, which can be overridden if custom behavior is desired. Useful strategies
     * to consider are to print to console (default), do nothing, or throw an exception.
     */
    fun freeRemaining(notify: (String) -> Unit = { println(it) }) {
        if (roots.isEmpty()) return

        run {
            val strBuilder = StringBuilder()
            strBuilder.append("Some disposables were not cleaned up:\n")
            roots.forEach { root ->
                strBuilder.append('\n')
                addIndentedNames(strBuilder, "", root)
            }
            notify(strBuilder.toString())
        }

        while (roots.isNotEmpty()) {
            // Avoid concurrent modification exception
            dispose(roots[0])
        }
    }

    private fun handleDisposeChildren(parent: Disposable) {
        val siblings = childrenOf.remove(parent)

        siblings?.forEach { child ->
            parentOf.remove(child)
            handleDisposeChildren(child)
            handleDispose(child)
        }
    }

    private fun handleDispose(disposable: Disposable) {
        if (disposable.disposed) {
            throw AlreadyDisposedException("Attempting to dispose a pre-disposed disposable")
        }
        disposable.dispose()
    }

    /**
     * Add the target [disposable]'s name as well as its children, indented, in breadth first order.
     */
    private fun addIndentedNames(strBuilder: StringBuilder, indent: String, disposable: ImmutableDisposable) {
        strBuilder.append(indent).append(disposable.toString()).append('\n')
        val nextIndent = "$indent  "
        childrenOf[disposable]?.forEach { child -> addIndentedNames(strBuilder, nextIndent, child) }
    }

    private fun ImmutableDisposable.isSelfOrDescendantOf(potentialAncestor: ImmutableDisposable): Boolean {
        return this === potentialAncestor || this.isDescendantOf(potentialAncestor)
    }

    private fun ImmutableDisposable.isDescendantOf(potentialAncestor: ImmutableDisposable): Boolean {
        var curr: ImmutableDisposable? = parentOf[this]
        while (curr != null) {
            if (curr === potentialAncestor) {
                return true
            }
            curr = parentOf[curr] ?: break
        }
        return false
    }
}

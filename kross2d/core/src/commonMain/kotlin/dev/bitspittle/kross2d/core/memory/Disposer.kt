package dev.bitspittle.kross2d.core.memory

import dev.bitspittle.kross2d.core.concurrency.synchronized

/**
 * A global object useful for explicitly tracking and releasing [Disposable] objects, when waiting on the GC to lazily
 * run isn't viable.
 *
 * When you instantiate a [Disposable] (or subclass), you should call [Disposer.register] to register it with this
 * global object. When done with the object, you should call [Disposer.dispose] to release it. (Note that [setParent]
 * will also register both the parent and child disposables if not already registered.)
 *
 * Finally, you should call [Disposer.freeRemaining] right before you exit your app, as that will report if any
 * disposables were not explicitly freed (meaning you have a memory leak!)
 *
 * Disposables can (and should!) be parented hierarchically, via [Disposer.setParent] (or
 * the convenience function [Disposable.setParent]):
 *
 * ```
 * // App, Project, and Resource are Disposable classes in this example
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
 *
 * This class is thread-safe, and calling relevant methods will block until completed.
 */
object Disposer {
    enum class AlreadyRegisteredStrategy {
        REREGISTER,
        ERROR,
        IGNORE;

        companion object {
            val DEFAULT = ERROR
        }
    }

    private val lock = Any()

    private val roots = mutableListOf<Disposable>()

    /** Map of a Disposable to any children registered with it **/
    private val childrenOf = mutableMapOf<Disposable, MutableList<Disposable>>()

    /** Map of a Disposable to its parent **/
    private val parentOf = mutableMapOf<Disposable, Disposable>()

    /**
     * Register a top-level disposable.
     *
     * Usually, it's better to register a disposable underneath a parent, so you can chain their release at the same
     * time. You can still call [setParent] later to move it.
     */
    fun <D : Disposable> register(disposable: D, alreadyRegisteredStrategy: AlreadyRegisteredStrategy = AlreadyRegisteredStrategy.DEFAULT) = synchronized(lock) {
        register(null, disposable, alreadyRegisteredStrategy)
    }

    /**
     * Register a disposable underneath a parent. When a caller requests for the parent to be
     * disposed, its children will all be disposed first (and so-on).
     *
     * Note that calling this method on a disposable that's already been registered will reparent
     * it (as long as doing so doesn't cause an infinite cycle, e.g. setting your kid as your
     * parent)
     */
    fun <D1 : Disposable, D2 : Disposable> register(parent: D1?, child: D2, alreadyRegisteredStrategy: AlreadyRegisteredStrategy = AlreadyRegisteredStrategy.DEFAULT) = synchronized(lock) {
        if (isRegistered(child)) {
            when (alreadyRegisteredStrategy) {
                AlreadyRegisteredStrategy.REREGISTER -> unregister(child)
                AlreadyRegisteredStrategy.ERROR -> throw DisposableException("A disposable can only be registered once")
                AlreadyRegisteredStrategy.IGNORE -> return@synchronized
            }
        }
        if (child.isDisposed) {
            throw DisposableException("Can't register a disposable that's been previously disposed")
        }

        if (parent != null) {
            setParent(parent, child)
        } else {
            roots.add(child)
        }
    }

    // Exposed for testing
    internal fun isRegistered(disposable: Disposable): Boolean = synchronized(lock) {
        return roots.contains(disposable) || parentOf.contains(disposable)
    }

    /**
     * Set up a relationship between a disposable and its parent.
     *
     * If either [newParent] or [child] is not registered after this point, they both will be after this call.
     *
     * If [child] already has a parent, it will be changed.
     */
    fun <D1 : Disposable, D2 : Disposable> setParent(newParent: D1, child: D2): Unit = synchronized(lock) {
        register(newParent, AlreadyRegisteredStrategy.IGNORE)

        if (newParent.isDisposed) {
            throw DisposableException("Tried to reparent onto a disposable that's been previously disposed")
        }

        if (child.isDisposed) {
            throw DisposableException("Tried to reparent a disposable that's been previously disposed")
        }

        // You are allowed to change the parent, but no paradoxes allowed!
        if (newParent.isSelfOrDescendantOf(child)) {
            throw DisposableException("Can't reparent a disposable underneath itself.")
        }

        parentOf[child]?.let { oldParent ->
            if (oldParent === newParent) {
                return // The parent didn't change, so this is a no-op request
            }
        }

        unregister(child)

        parentOf[child] = newParent
        childrenOf.getOrPut(newParent) { mutableListOf() }.add(child)
    }

    // Exposed for testing
    internal fun parentOf(disposable: Disposable): Disposable? = synchronized(lock) { parentOf[disposable] }

    /**
     * Move all children from one owner to another.
     *
     * This will fail if you try to move children to a new parent that, itself, is
     * owned by the first one.
     */
    fun transferChildren(from: Disposable, to: Disposable) = synchronized(lock) {
        if (from === to) {
            return
        }

        if (to.isDescendantOf(from)) {
            throw DisposableException("Can't transfer to a new parent owned by the existing parent.")
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
    fun dispose(disposable: Disposable) = synchronized(lock) {
        if (!isRegistered(disposable)) {
            throw DisposableException("Tried to dispose a value that was never registered")
        }

        handleDisposeChildren(disposable)
        unregister(disposable)
        handleDispose(disposable)
    }

    /**
     * A safe version of [dispose] that won't throw an exception on failure.
     */
    fun tryDispose(disposable: Disposable): Boolean = synchronized(lock) {
        try {
            dispose(disposable)
            return true
        } catch (_: DisposableException) {
            return false
        }
    }

    // IMPORTANT: This should only be called within a synchronized lock
    private fun unregister(disposable: Disposable) {
        parentOf.remove(disposable)?.let { parent ->
            childrenOf[parent]!!.let { children ->
                children.remove(disposable)
                if (children.isEmpty()) {
                    childrenOf.remove(parent)
                }
            }
        } ?: roots.remove(disposable)
    }

    /**
     * Free any remaining [Disposable]s. Ideally, all disposables should be released via [dispose]
     * calls already, but it's a good idea to run this before exiting your program.
     *
     * If any disposables are freed by this method, an error string will be sent to the passed-in
     * [notify] callback, which can be overridden if custom behavior is desired. Useful strategies
     * to consider are to print to console (default), do nothing, or throw an exception.
     */
    fun freeRemaining(notify: (String) -> Unit = { println(it) }) = synchronized(lock) {
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
        if (disposable.isDisposed) {
            throw DisposableException("Attempting to dispose a pre-disposed disposable")
        }
        disposable.dispose()
    }

    /**
     * Add the target [disposable]'s name as well as its children, indented, in breadth first order.
     */
    private fun addIndentedNames(strBuilder: StringBuilder, indent: String, disposable: Disposable) {
        strBuilder.append(indent).append(disposable.toString()).append('\n')
        val nextIndent = "$indent  "
        childrenOf[disposable]?.forEach { child -> addIndentedNames(strBuilder, nextIndent, child) }
    }

    private fun Disposable.isSelfOrDescendantOf(potentialAncestor: Disposable): Boolean {
        return this === potentialAncestor || this.isDescendantOf(potentialAncestor)
    }

    private fun Disposable.isDescendantOf(potentialAncestor: Disposable): Boolean {
        var curr: Disposable? = parentOf[this]
        while (curr != null) {
            if (curr === potentialAncestor) {
                return true
            }
            curr = parentOf[curr] ?: break
        }
        return false
    }
}

/**
 * Convenience function for calling [Disposer.setParent] indirectly, in a chain-friendly way
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
fun <D: Disposable> D.setParent(parent: Disposable): D {
    Disposer.setParent(parent, this)
    return this
}

/**
 * Convenience method for scoping a disposable to a block. After the block is finished, the disposable will be disposed.
 */
inline fun <D: Disposable> D.use(block: (D) -> Unit) {
    if (isDisposed) { throw DisposableException("Attempting to `use` a disposed disposable") }
    Disposer.register(this, Disposer.AlreadyRegisteredStrategy.IGNORE)
    try {
        block(this)
    }
    finally {
        Disposer.dispose(this)
    }
}

/**
 * Convenience function for registering an anonymous disposable
 */
fun Disposer.register(parent: Disposable? = null, block: () -> Unit): Disposable {
    return disposableOf(block).also { register(parent, it) }
}

/**
 * Convenience function for registering an empty anonymous disposable (useful for being using as a parent)
 */
fun Disposer.registerEmpty(parent: Disposable? = null): Disposable {
    return disposableOf().also { register(parent, it) }
}

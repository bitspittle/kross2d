package bitspittle.kross2d.core.memory

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
interface Disposable {
    /**
     * Release any resources held by this object.
     *
     * WARNING: You should NOT call this method directly; rather, call [Disposer.dispose] instead.
     * This way, you are guaranteed that this method will only ever be called once, and also any
     * children disposables will also be cleaned up.
     */
    fun dispose() {}
}

/**
 * Convenience method for scoping a disposable to a block. After the block is finished, the
 * disposable will be disposed.
 */
inline fun <D: Disposable> D.use(block: (D) -> Unit) {
    Disposer.register(this)
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
    return object : Disposable {
        override fun dispose() {
            block()
        }
    }
}

object Disposer {
    /** Map of a Disposable to any children registered with it **/
    private val childrenOf = mutableMapOf<Disposable, MutableList<Disposable>>()
    /** Map of a Disposable to its parent **/
    private val parentOf = mutableMapOf<Disposable, Disposable>()

    /**
     * This class maintains a tree structure of [Disposable] instances, and this stubbed instance
     * serves as the root of everything, so that any [Disposable] that a caller [register]s will
     * have something that can be set as its parent.
     */
    private val ROOT = object : Disposable {}

    /**
     * Register a top-level disposable.
     *
     * Usually, it's better to register a disposable underneath a parent, so you can chain their
     * release at the same time.
     *
     * Note that calling this method on a disposable that's already been registered will move it to
     * the top-level.
     */
    fun register(disposable: Disposable) {
        register(ROOT, disposable)
    }

    /**
     * Register a disposable underneath a parent. When a caller requests for the parent to be
     * disposed, its children will all be disposed first (and so-on).
     *
     * Note that calling this method on a disposable that's already been registered will reparent
     * it (as long as doing so doesn't cause an infinite cycle, e.g. setting your kid as your
     * parent)
     */
    fun register(parent: Disposable, child: Disposable) {
        parentOf[child]?.let { oldParent ->
            @Suppress("UnnecessaryVariable") // Renamed for readability
            val newParent = parent

            if (oldParent == newParent) {
                return // User requested a no-op move, so ignore
            }

            // You are allowed to change the parent, but no paradoxes allowed!
            if (newParent.isSelfOrDescendantOf(child)) {
                throw IllegalArgumentException("Can't reparent a Disposable underneath itself")
            }
            removeFromSiblings(child)
        }

        if (parent != ROOT && !parentOf.containsKey(parent)) {
            // If here, we want to register ourselves against a parent that itself was never
            // registered! Register it now at the top-level; worst case, it can always be
            // re-parented later.
            Disposer.register(parent)
        }

        parentOf[child] = parent
        childrenOf.getOrPut(parent) { mutableListOf() }.add(child)
    }

    /**
     * Move all disposable children from one owner to another.
     *
     * This will fail if you try to move a disposable's children to a disposable that, itself, is
     * owned by the first disposable.
     */
    fun transferChildren(from: Disposable, to: Disposable) {
        if (from === to) {
            return
        }
        if (to.isSelfOrDescendantOf(from)) {
            throw IllegalArgumentException("Can't transfer Disposables to a parent owned by the first.")
        }

        val newChildren = childrenOf.getOrPut(to) { mutableListOf() }
        childrenOf[from]?.let { newChildren.addAll(it) }
        childrenOf.remove(from)
        newChildren.forEach { child -> parentOf[child] = to }
    }

    fun isRegistered(disposable: Disposable) = parentOf.containsKey(disposable)

    /**
     * Release the resources held by this [Disposable], as well as all children disposables.
     *
     * Children will be cleaned up before the parent is.
     */
    fun dispose(disposable: Disposable) {
        if (!isRegistered(disposable)) {
            throw IllegalArgumentException("Attempted to dispose a Disposable that was already disposed or never registered: $disposable")
        }

        handleDisposeChildren(disposable)
        disposable.dispose()
        removeFromSiblings(disposable)
    }

    private fun removeFromSiblings(disposable: Disposable) {
        val parent = parentOf.remove(disposable)!!
        childrenOf[parent]!!.let { siblings ->
            siblings.remove(disposable)
            if (siblings.isEmpty()) {
                childrenOf.remove(parent)
            }
        }
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
        val rootDisposables = childrenOf[ROOT] ?: return

        run {
            val strBuilder = StringBuilder()
            strBuilder.append("Some disposables were not cleaned up:\n")
            rootDisposables.forEach { disposable ->
                strBuilder.append('\n')
                addIndentedNames(strBuilder, "", disposable) }
            notify(strBuilder.toString())
        }

        handleDisposeChildren(ROOT)
    }

    private fun handleDisposeChildren(disposable: Disposable) {
        childrenOf[disposable]?.forEach { child ->
            handleDisposeChildren(child)
            child.dispose()
        }
        childrenOf.remove(disposable)
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
        var currDisposable = this
        while (currDisposable != ROOT) {
            if (currDisposable == potentialAncestor) {
                return true
            }
            currDisposable = parentOf[currDisposable] ?: break
        }
        return false
    }
}

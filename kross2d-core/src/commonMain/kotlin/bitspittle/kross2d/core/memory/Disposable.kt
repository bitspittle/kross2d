package bitspittle.kross2d.core.memory

class AlreadyDisposedException(msg: String) : Exception(msg)

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
    val box = Disposer.register(this)
    try {
        block(this)
    }
    finally {
        Disposer.dispose(box)
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

/**
 * A wrapper class that boxes a target disposable, ensuring access to it verifies it hasn't already
 * been disposed.
 *
 * To open a box, you [deref] it. For example:
 *
 * ```
 * val image: Box<Image>
 *
 * // In GameState#draw
 * ctx.screen.drawImage(image.deref())
 * // or image.deref { ctx.screen.drawImage(it) }
 * ```
 *
 * Ideally, users shouldn't hold onto the target [Disposable] directly - instead, they should always
 * access it by dereferenicing this box. This way, the code will fail fast if there's ever a time
 * that a disposed object is accessed.
 *
 * APIs should think carefully about whether they expose a disposable object directly, vs. a box.
 * If you are confident that the code won't hold onto the disposable longer than it will live,
 * then sharing it is OK. Otherwise, share a `Box<Disposable>` instead.
 *
 * See also: [Disposer], which is responsible for boxing and disposing target [Disposable] values.
 * (A user is not allowed to create boxes themselves)
 */
class Box<out T: Disposable>internal constructor(value: T): Reference<T> {
    var disposed = false
        private set

    private var value: T? = value

    internal fun dispose() {
        value!!.let {
            disposed = true
            it.dispose()
            value = null
        }
    }

    override fun deref(): T {
        if (disposed) {
            throw AlreadyDisposedException("Tried to use a boxed value after it was already disposed.")
        }
        return value!!
    }

    override fun toString(): String {
        value?.let { return "Box { $it }" } ?: return "Box {}"
    }
}

/**
 * A global object useful for explicitly tracking and releasing [Disposable] objects, when waiting
 * on the GC to lazily run isn't viable.
 *
 * A disposer is responsible for wrapping a [Disposable] with a [Box]. Users probably shouldn't
 * even create their disposables without immediately boxing them:
 *
 * ```
 * // Best practice
 * val dbConn: Box<DbConn> = Disposer.register(DbConn())
 *
 * // Avoid
 * val dbConn = DbConn()
 * val dbConnBox = Disposer.register(dbConn)
 * ```
 *
 * Having a `Box<T>` in your API advertises that the item being boxed sits on top of a heavy
 * resource and needs to be explicitly disposed at some point.
 *
 * Disposables can (and should!) be chained, via [Disposer.register]:
 *
 * ```
 * val app = Disposer.register(App())
 * val project1 = Disposer.register(app, Project())
 * val project2 = Disposer.register(app, Project())
 * val resource = Disposer.register(project1, Resource())
 * ```
 *
 * To release a boxed disposable, just call [Disposer.dispose]:
 *
 * ```
 * Disposer.dispose(app)
 * assert(project1.disposed == true)
 * assert(resource.disposed == true)
 * ```
 */
object Disposer {
    class IllegalDisposerOperation(msg: String) : Exception(msg)

    /** Map of a Disposable to any children registered with it **/
    private val childrenOf = mutableMapOf<Box<Disposable>, MutableList<Box<Disposable>>>()

    /** Map of a Disposable to its parent **/
    private val parentOf = mutableMapOf<Box<Disposable>, Box<Disposable>>()

    private val boxRegistry = mutableMapOf<Disposable, Box<Disposable>>()

    /**
     * This class maintains a tree structure of [Disposable] instances, and this stubbed instance
     * serves as the root of everything, so that any [Disposable] that a caller [register]s will
     * have something that can be set as its parent.
     */
    private val ROOT = Box(disposable {})

    /**
     * Register a top-level disposable.
     *
     * Usually, it's better to register a disposable underneath a parent, so you can chain their
     * release at the same time.
     *
     * Note that calling this method on a disposable that's already been registered will move it to
     * the top-level.
     */
    fun <D : Disposable> register(disposable: D): Box<D> {
        return register(ROOT, disposable)
    }

    /**
     * Register a disposable underneath a boxed parent. When a caller requests for the parent to be
     * disposed, its children will all be disposed first (and so-on).
     *
     * Note that calling this method on a disposable that's already been registered will reparent
     * it (as long as doing so doesn't cause an infinite cycle, e.g. setting your kid as your
     * parent)
     */
    fun <D1 : Disposable, D2 : Disposable> register(parentBox: Box<D1>, child: D2): Box<D2> {
        val childBox = boxOf(child) ?: Box(child).also { boxRegistry[child] = it }
        reparent(parentBox, childBox)
        return childBox
    }

    /**
     * Like the other [register] method, but will box the [parent] as well if not already boxed.
     *
     * While it's encouraged to register against a [Box] for clarity, there are times where you
     * may not have one. For example, a class in its constructor may want to register some
     * children against itself, before the caller creating that class can itself box it. Other
     * times, you may have an API that exposes a [Disposable] and not its box, but it may still
     * make sense to register against it (e.g. a disposable representing the global lifetime of the
     * application itself.
     */
    fun <D1 : Disposable, D2 : Disposable> register(parent: D1, child: D2): Box<D2> {
        val parentBox = boxRegistry.getOrPut(parent) { register(parent) }

        boxOf(child)?.let { childBox ->
            reparent(parentBox, childBox)
            return childBox
        }

        return register(parentBox, child)
    }

    /**
     * Move a boxed disposable to a new parent.
     */
    fun <D1 : Disposable, D2 : Disposable> reparent(newParentBox: Box<D1>, childBox: Box<D2>) {
        parentOf[childBox]?.let { oldParentBox ->
            if (oldParentBox === newParentBox) {
                return // The parent didn't change, so this is a no-op request
            }

            // You are allowed to change the parent, but no paradoxes allowed!
            if (newParentBox.isSelfOrDescendantOf(childBox)) {
                throw IllegalDisposerOperation("Can't reparent a disposable underneath itself.")
            }
            removeFromSiblings(childBox)
        }

        parentOf[childBox] = newParentBox
        childrenOf.getOrPut(newParentBox) { mutableListOf() }.add(childBox)
    }

    /**
     * Move all boxed children from one owner to another.
     *
     * This will fail if you try to move children to a box that, itself, is
     * owned by the first one.
     */
    fun transferChildren(from: Box<Disposable>, to: Box<Disposable>) {
        if (from === to) {
            return
        }

        if (to.isSelfOrDescendantOf(from)) {
            throw IllegalDisposerOperation("Can't transfer boxes to a new parent owned by the first.")
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
    fun dispose(box: Box<Disposable>) {
        if (box.disposed) {
            throw AlreadyDisposedException("Tried to dispose a box that has already been disposed")
        }

        handleDisposeChildren(box)
        removeFromSiblings(box)
        handleDispose(box)
    }

    /**
     * Returns `true` if this raw [Disposable] has already been registered.
     */
    fun isRegistered(disposable: Disposable): Boolean = boxRegistry.containsKey(disposable)

    private fun <D : Disposable> boxOf(disposable: D): Box<D>? {
        @Suppress("UNCHECKED_CAST") // Disposable of type D is always registered as Box<D>
        return boxRegistry[disposable] as Box<D>?
    }

    private fun removeFromSiblings(box: Box<Disposable>) {
        val parent = parentOf.remove(box)!!
        childrenOf[parent]!!.let { siblings ->
            siblings.remove(box)
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
        val topLevelBoxes = childrenOf[ROOT] ?: return

        run {
            val strBuilder = StringBuilder()
            strBuilder.append("Some disposables were not cleaned up:\n")
            topLevelBoxes.forEach { box ->
                strBuilder.append('\n')
                addIndentedNames(strBuilder, "", box) }
            notify(strBuilder.toString())
        }

        handleDisposeChildren(ROOT)
    }

    private fun handleDisposeChildren(box: Box<Disposable>) {
        val siblings = childrenOf.remove(box)

        siblings?.forEach { childBox ->
            parentOf.remove(childBox)
            handleDisposeChildren(childBox)
            handleDispose(childBox)
        }
    }

    private fun handleDispose(box: Box<Disposable>) {
        boxRegistry.remove(box.deref())
        box.dispose()
    }

    /**
     * Add the target [disposable]'s name as well as its children, indented, in breadth first order.
     */
    private fun addIndentedNames(strBuilder: StringBuilder, indent: String, box: Box<Disposable>) {
        val disposable = box.deref()
        strBuilder.append(indent).append(disposable.toString()).append('\n')
        val nextIndent = "$indent  "
        childrenOf[box]?.forEach { childBox -> addIndentedNames(strBuilder, nextIndent, childBox) }
    }

    private fun Box<Disposable>.isSelfOrDescendantOf(potentialAncestor: Box<Disposable>): Boolean {
        var currBox = this
        while (currBox != ROOT) {
            if (currBox == potentialAncestor) {
                return true
            }
            currBox = parentOf[currBox] ?: break
        }
        return false
    }
}

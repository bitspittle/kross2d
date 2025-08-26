package dev.bitspittle.kross2d.core.memory

import dev.bitspittle.kross2d.core.concurrency.synchronized

/**
 * A simple, thread-safe reference counting solution for target [Disposable] objects.
 *
 * After being constructed with a callback that instantiates the target disposable, calling [inc] for the first time
 * will trigger the instantiation and register the disposable. Later, calling [dec] the same amount of times that [inc]
 * was previously called will result in the object being released and disposed. If [inc] is called again at that point,
 * a new instance will be created.
 *
 * Use [value] to query the current value of this class. It will be `null` if [inc] was not yet called (or [dec] was
 * called an equal number of times), or use [withValue] to access [value] within a block protected by an [inc] and [dec]
 * block (although understand this releases the value if the RC was not previously incremented.)
 */
class Rc<D: Disposable>(private val create: () -> D) {
    private val lock = Any()

    var value: D? = null
        get() = synchronized(lock) { field }
        private set

    private var counter = 0

    fun inc() {
        val toRegister: D? = synchronized(lock) {
            if (counter == 0) {
                value = create()
            }
            ++counter

            if (counter == 1) value else null
        }

        if (toRegister != null) {
            Disposer.register(toRegister)
        }
    }

    fun dec() {
        val toDispose: D? = synchronized(lock) {
            if (counter == 0) {
                throw IllegalStateException("Unbalanced ref counting; dec() called more than inc()")
            }

            --counter

            if (counter == 0) { value!!.also { value = null } } else null
        }

        if (toDispose != null) {
            Disposer.dispose(toDispose)
        }
    }
}

/**
 * Access the RC's value within guarded logic that ensures the internal state will stay consistent even with exceptions.
 *
 * @return a side effect value computed by the block (although in practice this will almost always be `Unit`). You
 *   are encouraged to not return or escape `D` from this block!
 */
fun <D: Disposable, R> Rc<D>.withValue(block: (D) -> R): R {
    var acquired = false
    try {
        inc()
        acquired = true
        return block(value!!)
    } finally {
        if (acquired) dec()
    }
}

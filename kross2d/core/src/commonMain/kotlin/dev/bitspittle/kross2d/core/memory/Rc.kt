package dev.bitspittle.kross2d.core.memory

import dev.bitspittle.kross2d.core.concurrency.Synchronized
import dev.bitspittle.kross2d.core.concurrency.Volatile

/**
 * A simple, thread-safe reference counting solution for target [Disposable] objects.
 *
 * After being constructed with a callback that instantiates the target disposable, calling [inc]
 * for the first time will trigger the instantiation and register the disposable. Later, calling
 * [dec] the same amount of times that [inc] was previously called will result in the object being
 * released and disposed. If [inc] is called again at that point, a new instance will be created.
 *
 * Use [value] to query the current value of this class. It will be `null` if [inc] was not yet
 * called (or [dec] was called an equal number of times)
 */
class Rc<D: Disposable>(private val create: () -> D) {
    var value: D? = null
        private set

    @Volatile
    private var counter = 0

    @Synchronized
    fun inc() {
        if (counter == 0) {
            value = create()
        }
        ++counter
    }

    @Synchronized
    fun dec() {
        if (counter == 0) {
            throw IllegalStateException("Unbalanced ref counting; dec() called more than inc()")
        }

        --counter

        if (counter == 0) {
            Disposer.dispose(value!!)
            value = null
        }
    }
}
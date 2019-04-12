package bitspittle.kross2d.core.memory

import kotlin.jvm.Synchronized
import kotlin.jvm.Volatile

/**
 * A simple, thread-safe reference counting solution for target [Disposable] objects.
 *
 * After being constructed with a callback that instantiates the target disposable, calling [inc]
 * for the first time will trigger the instantiation and register the disposable. Later, calling
 * [dec] the same amount of times that [inc] was previously called will result in the object being
 * released and disposed. If [inc] is called again at that point, a new instance will be created.
 */
class Rc<T: Disposable>(private val create: () -> T) {
    var value: Box<T>? = null
        private set

    @Volatile
    private var counter = 0

    @Synchronized
    fun inc() {
        if (counter == 0) {
            value = Disposer.register(create())
        }
        ++counter
    }

    @Synchronized
    fun dec() {
        if (counter == 0) {
            throw IllegalStateException("Unbalanced ref counting; decremented more than incremented")
        }

        --counter

        if (counter == 0) {
            Disposer.dispose(value!!)
            value = null
        }
    }
}
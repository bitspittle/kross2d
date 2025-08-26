package dev.bitspittle.kross2d.core.event

import dev.bitspittle.kross2d.core.concurrency.synchronized
import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.Disposer
import dev.bitspittle.kross2d.core.memory.register


/**
 * The part of an event responsible for registering listeners but not firing. This can safely be exposed publicly.
 *
 * Here, `onKeyPressed` would be exposed as an [Event]:
 *
 * ```
 * interface Keyboard {
 *    val onKeyPressed: ObservableEvent<Key>
 * }
 *
 * keyboard.onKeyPressed += { (key) -> ... }
 * ```
 *
 * Then, a class that implemented that Keyboard interface would internally create an [EventEmitter] which allows the
 * caller to trigger it.
 *
 * @param onAdded A callback registered by the event owner which will be triggered everytime a new event handler is
 * added, allowing the event owner to trigger it immediately (e.g. if the event listener is added *after* the initial
 * event happened).
 *
 * See also: [EventEmitter]
 */
abstract class Event<P>(private val onAdded: ((P) -> Unit) -> Unit) {
    private val lock = Any()

    protected val observers: MutableList<(P) -> Unit> = mutableListOf()

    operator fun plusAssign(observer: (P) -> Unit) {
        synchronized(lock) {
            observers.add(observer)
        }
        onAdded(observer)
    }

    operator fun plusAssign(scopedObserver: ScopedObserver<P>) {
        Disposer.register(scopedObserver.scope) { minusAssign(scopedObserver.observer) }
        plusAssign(scopedObserver.observer)
    }

    operator fun minusAssign(observer: (P) -> Unit) {
        synchronized(lock) {
            observers.remove(observer)
        }
    }
}

/**
 * The full event class, supporting invocation as well as listening.
 *
 * A class with an event should use the common pattern:
 *
 * ```
 * private val Event<EventParams> _event = Event<EventParams>()
 * public val ObservableEvent<EventParams> event = _event
 *
 * fun funcThatFiresEvent() {
 *   ...
 *   val params = EventParams(...)
 *   _event(params)
 * }
 *
 * ```
 *
 * See also: [Event]
 */
class EventEmitter<P>(onAdded: ((P) -> Unit) -> Unit = {}) : Event<P>(onAdded) {
    operator fun invoke(params: P) {
        observers.forEach { observer -> observer(params) }
    }

    fun clear() {
        observers.clear()
    }
}

/**
 * Create an observer that will get automatically cleaned up when it goes out of scope.
 *
 * This is particularly useful in game states:
 *
 * ```
 * // In enter
 * // Prefer this:
 * globalLives.onChanged += ScopedObserver(ctx.scopes.state) { updateUi() }
 *
 * // Over this:
 * // globalLives.onChanged += { updateUi() }
 * ```
 *
 * to ensure that listeners won't stay attached after the current state exits, as well as to
 * prevent multiple listeners from being added if `enter` is called multiple times.
 */
class ScopedObserver<P>(internal val scope: Disposable, internal val observer: (P) -> Unit)

package dev.bitspittle.kross2d.core.event

import dev.bitspittle.kross2d.core.concurrency.synchronized
import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.Disposer
import dev.bitspittle.kross2d.core.memory.register


/**
 * The part of an event responsible for registering listeners but not firing. This can safely be exposed publicly.
 *
 * Here, `keyPressed` would be exposed as an [Event]:
 *
 * ```
 * class Keyboard {
 *    val keyPressed: Event<Key> = ...
 * }
 *
 * keyboard.keyPressed += { (key) -> ... }
 * ```
 *
 * NOTE: We suggest a naming convention for events which does NOT begin with `on`. For example, above we use
 * `keyPressed` and *not* `onKeyPressed`. This is consistent with how properties are generally named. If you wanted to
 * create and store a listener behind a variable, that could be a good place to use `on`, as in
 *
 * ```
 * val onKeyPressed: (Key) -> Unit = { ... }`
 * keyboard.keyPressed += onKeyPressed
 *```
 *
 * See [EventEmitter] for more information about triggering an event.
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
 * private val _event = EventEmitter<EventParams>()
 * val Event<EventParams> event = _event
 *
 * fun funcThatFiresEvent() {
 *   ...
 *   val params = EventParams(...)
 *   _event(params)
 * }
 * ```
 *
 * We recommend using the progressive tense for events that are just about to happen (and perhaps the user can
 * update params to prevent or affect what will occur) and past tense for events that just happened.
 *
 * For example:
 *
 * ```
 * private val _closing = EventEmitter<ClosingParams>()
 * val closing: Event<ClosingParams> = _closing
 *
 * private val _closed = EventEmitter<ClosedParams>()
 * val closed: Event<ClosedParams> = _closed
 *
 * fun close() {
 *   val closingParams = ClosingParams()
 *   _closing(closingParams)
 *   if (closingParams.shouldAbort) return
 *
 *   _closed(ClosedParams(...))
 * }
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
 * This is particularly useful for automatically releasing listeners when game states change:
 *
 * ```
 * // In enter
 * // Prefer this:
 * user.healthChanged += ScopedObserver(ctx.scopes.currState) { health -> updateLifeBar(health) }
 *
 * // Over this:
 * // val onHealthChanged: (Int) -> Unit = { health -> updateLifeBar(health) }
 * // user.healthChanged += onHealthChanged
 * // ... later, in dispose ...
 * // user.healthChanged -= onHealthChanged
 * ```
 */
class ScopedObserver<P>(internal val scope: Disposable, internal val observer: (P) -> Unit)

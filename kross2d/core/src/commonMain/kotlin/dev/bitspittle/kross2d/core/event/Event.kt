package dev.bitspittle.kross2d.core.event

import dev.bitspittle.kross2d.core.memory.ImmutableDisposable
import dev.bitspittle.kross2d.core.memory.disposable

/**
 * The part of an event responsible for registering listeners but not firing. This can safely be
 * exposed publicly.
 *
 * Here, `onKeyPressed` would be exposed as an [ObservableEvent]:
 *
 * ```
 * interface Keyboard {
 *    val onKeyPressed: ObservableEvent<Key>
 * }
 *
 * keyboard.onKeyPressed += { (key) -> ... }
 * ```
 *
 * Finally, an [onAdded] callback can be registered for this event, which gives owners of the event
 * a chance to respond to a new listener getting added, e.g. maybe its an 'onLoaded' listener being
 * added to an object that already loaded earlier, so the observer should fire immediately.
 *
 * See also: [Event]
 */
abstract class ObservableEvent<P>(private val onAdded: () -> Unit) {
    protected val observers: MutableList<(P) -> Unit> = mutableListOf()

    operator fun plusAssign(observer: (P) -> Unit) {
        observers.add(observer)
        onAdded()
    }

    operator fun plusAssign(scopedObserver: ScopedObserver<P>) {
        disposable(scopedObserver.scope) { minusAssign(scopedObserver.observer) }
        plusAssign(scopedObserver.observer)
    }

    operator fun minusAssign(observer: (P) -> Unit) {
        observers.remove(observer)
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
 * See also: [ObservableEvent]
 */
class Event<P>(onAdded: () -> Unit = {}) : ObservableEvent<P>(onAdded) {
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
class ScopedObserver<P>(internal val scope: ImmutableDisposable, internal val observer: (P) -> Unit)

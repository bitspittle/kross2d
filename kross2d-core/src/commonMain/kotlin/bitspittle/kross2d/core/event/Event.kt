package bitspittle.kross2d.core.event

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

    // TODO: Worry about memory leaks? Maybe change te API to `event.listenWith(owner) { (param) -> ... }`
    operator fun plusAssign(observer: (P) -> Unit) {
        observers.add(observer)
        onAdded()
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
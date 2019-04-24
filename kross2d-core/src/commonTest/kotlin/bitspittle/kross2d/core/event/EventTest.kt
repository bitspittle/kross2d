package bitspittle.kross2d.core.event

import bitspittle.kross2d.core.memory.Disposer
import bitspittle.kross2d.core.memory.disposable
import bitspittle.truthish.assertThat
import kotlin.test.Test

class EventTest {
    var _voidEvent = Event<Unit>()
    val voidEvent: ObservableEvent<Unit> = _voidEvent

    var _intEvent = Event<Int>()
    val intEvent: ObservableEvent<Int> = _intEvent

    data class EventArgs(val sender: Any, val message: String)
    var _argEvent = Event<EventArgs>()
    val argEvent: ObservableEvent<EventArgs> = _argEvent

    @Test
    fun canListenToEventViaObservable() {
        var count = 0
        voidEvent += { count++ }

        assertThat(count).isEqualTo(0)

        _voidEvent(Unit)
        assertThat(count).isEqualTo(1)

        _voidEvent(Unit)
        assertThat(count).isEqualTo(2)
    }

    @Test
    fun canListenToPrimitiveValues() {
        var lastValue = 0
        intEvent += { lastValue = it }

        assertThat(lastValue).isEqualTo(0)

        _intEvent(23)
        assertThat(lastValue).isEqualTo(23)

        _intEvent(-9)
        assertThat(lastValue).isEqualTo(-9)
    }

    @Test
    fun canListenToComplexValues() {
        val sender1 = object : Any() {}
        val sender2 = object : Any() {}

        var lastSender: Any? = null
        var lastMessage: String? = null

        argEvent += { args ->
            lastSender = args.sender
            lastMessage = args.message
        }

        assertThat(lastSender).isNull()
        assertThat(lastMessage).isNull()

        _argEvent(EventArgs(sender1, "Hello"))
        assertThat(lastSender).isSameAs(sender1)
        assertThat(lastMessage).isEqualTo("Hello")

        _argEvent(EventArgs(sender2, "Goodbye"))
        assertThat(lastSender).isSameAs(sender2)
        assertThat(lastMessage).isEqualTo("Goodbye")
    }

    @Test
    fun canClearEvents() {
        var sum = 0

        voidEvent += { sum++ }
        voidEvent += { sum++ }
        voidEvent += { sum++ }

        _voidEvent(Unit)
        assertThat(sum).isEqualTo(3)

        _voidEvent(Unit)
        assertThat(sum).isEqualTo(6)

        _voidEvent.clear()
        _voidEvent(Unit)
        assertThat(sum).isEqualTo(6)
    }

    @Test
    fun canRemoveListeners() {
        var sum = 0

        val listener1: (Unit) -> Unit = { sum++ }
        val listener2: (Unit) -> Unit = { sum++ }

        voidEvent += listener1
        voidEvent += listener2

        _voidEvent(Unit)
        assertThat(sum).isEqualTo(2)

        voidEvent -= listener1
        _voidEvent(Unit)
        assertThat(sum).isEqualTo(3)

        voidEvent -= listener2
        _voidEvent(Unit)
        assertThat(sum).isEqualTo(3)
    }

    @Test
    fun canScopeListeners() {
        val scope = disposable {}

        var sum = 0
        voidEvent += ScopedObserver(scope) { sum++ }

        _voidEvent(Unit)
        _voidEvent(Unit)
        assertThat(sum).isEqualTo(2)

        Disposer.dispose(scope)
        assertThat(sum).isEqualTo(2)

        _voidEvent(Unit)
        assertThat(sum).isEqualTo(2)
    }
}

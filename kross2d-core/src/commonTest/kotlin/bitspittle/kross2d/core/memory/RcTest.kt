package bitspittle.kross2d.core.memory

import bitspittle.truthish.assertThat
import bitspittle.truthish.assertThrows
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.fail

class RcTest {
    class TestDisposable : Disposable {
        var disposed = false
        override fun dispose() {
            disposed = true
        }
    }

    @AfterTest
    fun afterTest() {
        // Quietly free any disposables left over from the last test
        Disposer.freeRemaining { }
    }

    @Test
    fun testIncAndDec() {
        val rc = Rc { TestDisposable() }
        assertThrows<Exception> { rc.deref() }

        rc.inc()
        rc.deref { d ->
            for (i in 0..10) {
                rc.inc()
            }
            assertThat(rc.deref()).isSameAs(d)
            for (i in 0..10) {
                rc.dec()
            }
            assertThat(rc.deref()).isSameAs(d)
            assertThat(d.disposed).isFalse()

            rc.dec()
            assertThrows<Exception> { rc.deref() }
            assertThat(d.disposed).isTrue()

            rc.inc()
            assertThat(rc.deref()).isNotSameAs(d)
            rc.dec()
        }

        assertThrows<Exception> { rc.deref() }
        assertThrows<Exception> { rc.deref { } }
    }
}
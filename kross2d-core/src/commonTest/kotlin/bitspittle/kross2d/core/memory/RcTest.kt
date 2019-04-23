package bitspittle.kross2d.core.memory

import bitspittle.truthish.assertThat
import bitspittle.truthish.assertThrows
import kotlin.test.AfterTest
import kotlin.test.Test

class RcTest {
    @AfterTest
    fun afterTest() {
        // Quietly free any disposables left over from the last test
        Disposer.freeRemaining { }
    }

    @Test
    fun testIncAndDec() {
        val rc = Rc { disposable {} }
        assertThat(rc.value).isNull()

        rc.inc()
        rc.value!!.let { d ->
            for (i in 0..10) {
                rc.inc()
            }
            assertThat(rc.value!!).isSameAs(d)
            for (i in 0..10) {
                rc.dec()
            }
            assertThat(rc.value!!).isSameAs(d)
            assertThat(d.disposed).isFalse()

            rc.dec()
            assertThat(rc.value as Disposable?).isNull()
            assertThat(d.disposed).isTrue()

            rc.inc()
            assertThat(rc.value!!).isNotSameAs(d)
            rc.dec()
        }

        assertThrows<IllegalStateException> {
            rc.dec()
        }
    }
}
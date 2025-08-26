package dev.bitspittle.kross2d.core.memory

import com.varabyte.truthish.assertThat
import com.varabyte.truthish.assertThrows
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
        val rc = Rc { disposableOf { } }

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
            assertThat(d.disposed).isTrue()

            assertThat(rc.value).isNull()

            rc.inc()
            assertThat(rc.value!!).isNotSameAs(d)
            rc.dec()
        }

        assertThrows<IllegalStateException> {
            rc.dec()
        }
    }

    @Test
    fun testRcRecoversFromCreationFailing() {
        var allowCreatingDisposable = false
        val rc = Rc { if (allowCreatingDisposable) disposableOf { } else error("boom") }

        assertThat(rc.value).isNull()

        assertThrows<IllegalStateException> {
            rc.inc()
        }
        assertThat(rc.value).isNull()

        // RC can recover
        allowCreatingDisposable = true
        rc.inc()
        assertThat(rc.value).isNotNull()
        rc.dec()

        assertThrows<IllegalStateException> {
            rc.dec()
        }
    }

    @Test
    fun testWithValue() {

        // withValue basic behavior for initial RC (cleans up afterwards)
        run {
            val rc = Rc { disposableOf { } }

            var withValueCalled = false
            rc.withValue { d ->
                assertThat(rc.value).isSameAs(d)
                withValueCalled = true
            }
            assertThat(withValueCalled).isTrue()
            assertThat(rc.value).isNull()
        }

        // withValue basic behavior for already incremented RC
        run {
            val rc = Rc { disposableOf { } }

            var withValueCalled = false
            rc.inc()
            val d = rc.value!!
            rc.withValue { d2 ->
                assertThat(d2).isSameAs(d)
                withValueCalled = true
            }
            assertThat(withValueCalled).isTrue()
            assertThat(rc.value).isSameAs(d)
            rc.dec()
            assertThat(rc.value).isNull()
        }

        // withValue can return a value
        run {
            val rc = Rc { disposableOf { } }

            var withValueCalled = false
            val result = rc.withValue {
                withValueCalled = true
                42
            }
            assertThat(withValueCalled).isTrue()
            assertThat(result).isEqualTo(42)
            assertThat(rc.value).isNull()
        }


        // withValue cleans up after exceptions
        run {
            val rc = Rc { disposableOf { } }

            var withValueCalled = false
            assertThrows<IllegalStateException> {
                rc.withValue {
                    withValueCalled = true
                    error("boom")
                }
            }
            assertThat(withValueCalled).isTrue()
            assertThat(rc.value).isNull()
        }
    }
}
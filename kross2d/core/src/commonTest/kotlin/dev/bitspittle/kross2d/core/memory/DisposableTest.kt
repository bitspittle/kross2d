package dev.bitspittle.kross2d.core.memory

import com.varabyte.truthish.assertThat
import com.varabyte.truthish.assertThrows
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.fail

// Disposer.register assigned to vars even if not needed, for readability
@Suppress("UNUSED_VARIABLE")
class DisposableTest {
    class TestDisposable(parent: TestDisposable? = null, private val displayName: String? = null) : Disposable(autoRegister = false) {
        init {
            Disposer.register(parent, this)
        }

        constructor(displayName: String): this(null, displayName)
        override fun toString() = "TestDisposable { $displayName }"
    }

    @AfterTest
    fun afterTest() {
        // Quietly free any disposables left over from the last test
        Disposer.freeRemaining { }
    }

    @Test
    fun canControlRegistrationStrategyWhenAlreadyRegistered() {
        val root = TestDisposable()
        val d1 = TestDisposable(root)
        val d2 = TestDisposable(root)

        val newParent = TestDisposable()

        assertThat(Disposer.isRegistered(d1)).isTrue()
        assertThat(Disposer.isRegistered(d2)).isTrue()
        assertThat(Disposer.parentOf(d1)).isSameAs(root)
        assertThat(Disposer.parentOf(d2)).isSameAs(root)

        assertThrows<DisposableException> {
            Disposer.register(d1, Disposer.AlreadyRegisteredStrategy.ERROR)
        }
        assertThrows<DisposableException> {
            Disposer.register(newParent, d2, Disposer.AlreadyRegisteredStrategy.ERROR)
        }

        Disposer.register(d1, Disposer.AlreadyRegisteredStrategy.REREGISTER)
        assertThat(Disposer.parentOf(d1)).isNull()
        Disposer.register(newParent, d2, Disposer.AlreadyRegisteredStrategy.REREGISTER)
        assertThat(Disposer.parentOf(d2)).isSameAs(newParent)

        Disposer.register(root, d1, Disposer.AlreadyRegisteredStrategy.IGNORE)
        assertThat(Disposer.parentOf(d1)).isNull()
        Disposer.register(root, d2, Disposer.AlreadyRegisteredStrategy.IGNORE)
        assertThat(Disposer.parentOf(d2)).isSameAs(newParent)
    }

    @Test
    fun testBasicDispose() {
        val d1 = TestDisposable()
        val d2 = TestDisposable()

        var disposeEventCount = 0
        d1.disposed += { disposeEventCount++ }
        d2.disposed += { disposeEventCount++ }

        assertThat(d1.isDisposed).isFalse()
        assertThat(d2.isDisposed).isFalse()
        assertThat(disposeEventCount).isEqualTo(0)

        Disposer.dispose(d2)
        assertThat(d1.isDisposed).isFalse()
        assertThat(d2.isDisposed).isTrue()
        assertThat(disposeEventCount).isEqualTo(1)

        Disposer.dispose(d1)
        assertThat(d1.isDisposed).isTrue()
        assertThat(d2.isDisposed).isTrue()
        assertThat(disposeEventCount).isEqualTo(2)

        // The disposed event fires immediately if already disposed
        d1.disposed += { disposeEventCount++ }
        assertThat(disposeEventCount).isEqualTo(3)
    }

    @Test
    fun cannotDisposeIfAlreadyDisposed() {
        val d = TestDisposable()
        Disposer.dispose(d)
        assertThrows<DisposableException> {
            Disposer.dispose(d)
        }

        assertThat(Disposer.tryDispose(d)).isFalse()
    }

    @Test
    fun reparentingAllowedIfNoParadox() {
        val grandparent = TestDisposable()
        val parent = TestDisposable()
        val child = TestDisposable()

        Disposer.setParent(grandparent, parent)
        Disposer.setParent(parent, child)

        assertThrows<DisposableException> {
            Disposer.setParent(child, grandparent)
        }

        assertThrows<DisposableException> {
            Disposer.setParent(grandparent, grandparent)
        }
    }

    @Test
    fun reparentingNotAllowedAgainstDisposedParent() {
        val parent = TestDisposable()
        val child = TestDisposable()

        Disposer.dispose(parent)

        assertThrows<DisposableException> {
            Disposer.setParent(parent, child)
        }
    }

    @Test
    fun reparentingNotAllowedWithDisposedChild() {
        val parent = TestDisposable()
        val child = TestDisposable()

        Disposer.dispose(child)

        assertThrows<DisposableException> {
            Disposer.setParent(parent, child)
        }
    }

    @Test
    fun canDisposeRecursivelyViaParents() {
        val b1 = TestDisposable()
        val b11 = TestDisposable(b1)
        val b111 = TestDisposable(b11)
        val b1111 = TestDisposable(b111)
        val b2 = TestDisposable()

        Disposer.dispose(b111)

        assertThat(b1.isDisposed).isFalse()
        assertThat(b11.isDisposed).isFalse()
        assertThat(b111.isDisposed).isTrue()
        assertThat(b1111.isDisposed).isTrue()
        assertThat(b2.isDisposed).isFalse()

        Disposer.dispose(b1)
        assertThat(b1.isDisposed).isTrue()
        assertThat(b11.isDisposed).isTrue()
        assertThat(b2.isDisposed).isFalse()

        Disposer.dispose(b2)
        assertThat(b2.isDisposed).isTrue()
    }

    @Test
    fun canRegisterAndDisposeViaUse() {
        // Basic usage
        run {
            var onDisposed = false
            val d = disposableOf { onDisposed = true }
            d.use {}
            assertThat(d.isDisposed).isTrue()
            assertThat(onDisposed).isTrue()
        }

        // use block still disposes even if an exception is thrown
        run {
            var onDisposed = false
            val d = disposableOf { onDisposed = true }
            assertThrows<RuntimeException> {
                d.use {
                    throw RuntimeException()
                }
            }
            assertThat(d.isDisposed).isTrue()
            assertThat(onDisposed).isTrue()
        }
    }

    @Test
    fun canCreateDisposableViaConvenienceConstructionMethod() {
        var onDisposed = false
        val d = Disposer.register { onDisposed = true }

        assertThat(onDisposed).isFalse()
        Disposer.dispose(d)
        assertThat(onDisposed).isTrue()
    }

    @Test
    fun canTransferOwnershipOfDisposables() {
        val parent1 = TestDisposable()
        val parent2 = TestDisposable()

        val child1 = TestDisposable(parent1)
        val child2 = TestDisposable(parent1)
        val child3 = TestDisposable(parent1)

        Disposer.transferChildren(from = parent1, to = parent2)

        Disposer.dispose(parent1)
        assertThat(child1.isDisposed).isFalse()
        assertThat(child2.isDisposed).isFalse()
        assertThat(child3.isDisposed).isFalse()

        Disposer.dispose(parent2)
        assertThat(child1.isDisposed).isTrue()
        assertThat(child2.isDisposed).isTrue()
        assertThat(child3.isDisposed).isTrue()

        // All disposables should have been removed
        Disposer.freeRemaining { fail(it) }
    }

    @Test
    fun transferringOwernshipFromAndToTheSameLeavesChildrenUnmoved() {
        val parent = TestDisposable()

        val child1 = TestDisposable(parent)
        val child2 = TestDisposable(parent)
        val child3 = TestDisposable(parent)

        Disposer.transferChildren(from = parent, to = parent)

        Disposer.dispose(parent)
        assertThat(child1.isDisposed).isTrue()
        assertThat(child2.isDisposed).isTrue()
        assertThat(child3.isDisposed).isTrue()
    }

    @Test
    fun transferringOwernshipToAChildDisposableThrowsAnException() {
        val parent = TestDisposable()

        val child1 = TestDisposable(parent)
        val child2 = TestDisposable(parent)
        val child3 = TestDisposable(parent)

        assertThrows<DisposableException> {
            Disposer.transferChildren(from = parent, to = child2)
        }
    }

    @Test
    fun testFreeRemainingMessage() {
        val b1 = TestDisposable("b1")
        val b11 = TestDisposable(b1, "b11")
        val b12 = TestDisposable(b1, "b12")
        val b121 = TestDisposable(b12, "b121")
        val b13 = TestDisposable(b1, "b13")
        val b131 = TestDisposable(b13, "b131")
        val b132 = TestDisposable(b13, "b132")

        val b2 = TestDisposable("b2")
        val b21 = TestDisposable(b2, "b21")
        val b22 = TestDisposable(b2,"b22")
        val b221 = TestDisposable(b22, "b221")
        val b2211 = TestDisposable(b221, "b2211")

        val b3 = TestDisposable("b3")
        val b31 = TestDisposable(b3, "b31")

        Disposer.freeRemaining {
            assertThat(it.trim()).isEqualTo(
                """
                Some disposables were not cleaned up:

                TestDisposable { b1 }
                  TestDisposable { b11 }
                  TestDisposable { b12 }
                    TestDisposable { b121 }
                  TestDisposable { b13 }
                    TestDisposable { b131 }
                    TestDisposable { b132 }

                TestDisposable { b2 }
                  TestDisposable { b21 }
                  TestDisposable { b22 }
                    TestDisposable { b221 }
                      TestDisposable { b2211 }

                TestDisposable { b3 }
                  TestDisposable { b31 }
                """.trimIndent()
            )
        }

        // Second call should be a no-op. Everything was already freed!
        Disposer.freeRemaining { fail(it) }
    }
}

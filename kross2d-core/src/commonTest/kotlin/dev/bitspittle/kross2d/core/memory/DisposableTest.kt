package dev.bitspittle.kross2d.core.memory

import dev.bitspittle.kross2d.core.memory.Disposer.IllegalDisposerOperation
import com.varabyte.truthish.assertThat
import com.varabyte.truthish.assertThrows
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.fail

// Disposer.register assigned to vars even if not needed, for readability
@Suppress("UNUSED_VARIABLE")
class DisposableTest {
    class TestDisposable(parent: TestDisposable? = null, private val displayName: String? = null) : Disposable(parent) {
        constructor(displayName: String): this(null, displayName)
        override fun toString() = "TestDisposable { $displayName }"
    }

    @AfterTest
    fun afterTest() {
        // Quietly free any disposables left over from the last test
        Disposer.freeRemaining { }
    }

    @Test
    fun disposablesAutomaticallyRegisteredAndCallingTwiceThrows() {
        val d1 = TestDisposable()
        val d2 = TestDisposable()

        // Normally, `register` is internal so users can't call it themselves, but just in
        // case, we verify if we make a logic mistake in this library, we'd throw
        assertThrows<IllegalDisposerOperation> {
            Disposer.register(d1)
        }
        assertThrows<IllegalDisposerOperation> {
            Disposer.register(d1, d2)
        }
    }

    @Test
    fun testBasicDispose() {
        val d1 = TestDisposable()
        val d2 = TestDisposable()

        assertThat(d1.disposed).isFalse()
        assertThat(d2.disposed).isFalse()

        Disposer.dispose(d2)
        assertThat(d1.disposed).isFalse()
        assertThat(d2.disposed).isTrue()

        Disposer.dispose(d1)
        assertThat(d1.disposed).isTrue()
        assertThat(d2.disposed).isTrue()
    }

    @Test
    fun cannotDisposeIfAlreadyDisposed() {
        val d = TestDisposable()
        Disposer.dispose(d)
        assertThrows<AlreadyDisposedException> {
            Disposer.dispose(d)
        }

        assertThat(Disposer.tryDispose(d)).isFalse()
    }

    @Test
    fun reparentingAllowedIfNoParadox() {
        val grandparent = TestDisposable()
        val parent = TestDisposable()
        val child = TestDisposable()

        Disposer.reparent(grandparent, parent)
        Disposer.reparent(parent, child)

        assertThrows<IllegalDisposerOperation> {
            Disposer.reparent(child, grandparent)
        }

        assertThrows<IllegalDisposerOperation> {
            Disposer.reparent(grandparent, grandparent)
        }
    }

    @Test
    fun reparentingNotAllowedAgainstDisposedParent() {
        val parent = TestDisposable()
        val child = TestDisposable()

        Disposer.dispose(parent)

        assertThrows<AlreadyDisposedException> {
            Disposer.reparent(parent, child)
        }
    }

    @Test
    fun reparentingNotAllowedWithDisposedChild() {
        val parent = TestDisposable()
        val child = TestDisposable()

        Disposer.dispose(child)

        assertThrows<AlreadyDisposedException> {
            Disposer.reparent(parent, child)
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

        assertThat(b1.disposed).isFalse()
        assertThat(b11.disposed).isFalse()
        assertThat(b111.disposed).isTrue()
        assertThat(b1111.disposed).isTrue()
        assertThat(b2.disposed).isFalse()

        Disposer.dispose(b1)
        assertThat(b1.disposed).isTrue()
        assertThat(b11.disposed).isTrue()
        assertThat(b2.disposed).isFalse()

        Disposer.dispose(b2)
        assertThat(b2.disposed).isTrue()
    }

    @Test
    fun canRegisterAndDisposeViaUse() {
        // Basic usage
        run {
            val d = TestDisposable()
            d.use {}
            assertThat(d.disposed).isTrue()
        }

        // use block still disposes even if an exception is thrown
        run {
            val d = TestDisposable()
            assertThrows<RuntimeException> {
                d.use {
                    throw RuntimeException()
                }
            }
            assertThat(d.disposed).isTrue()
        }
    }

    @Test
    fun canCreateDisposableViaConvenienceConstructionMethod() {
        var onDisposed = false
        val d = disposable { onDisposed = true }

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
        assertThat(child1.disposed).isFalse()
        assertThat(child2.disposed).isFalse()
        assertThat(child3.disposed).isFalse()

        Disposer.dispose(parent2)
        assertThat(child1.disposed).isTrue()
        assertThat(child2.disposed).isTrue()
        assertThat(child3.disposed).isTrue()

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
        assertThat(child1.disposed).isTrue()
        assertThat(child2.disposed).isTrue()
        assertThat(child3.disposed).isTrue()
    }

    @Test
    fun transferringOwernshipToAChildDisposableThrowsAnException() {
        val parent = TestDisposable()

        val child1 = TestDisposable(parent)
        val child2 = TestDisposable(parent)
        val child3 = TestDisposable(parent)

        assertThrows<IllegalDisposerOperation> {
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

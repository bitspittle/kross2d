package bitspittle.kross2d.core.memory

import bitspittle.kross2d.core.memory.Disposer.IllegalDisposerOperation
import bitspittle.truthish.assertThat
import bitspittle.truthish.assertThrows
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.fail

// Disposer.register assigned to vars even if not needed, for readability
@Suppress("UNUSED_VARIABLE")
class DisposableTest {
    class TestDisposable(private val displayName: String? = null) : Disposable {
        var disposed = false
        override fun dispose() {
            disposed = true
        }

        override fun toString() = "TestDisposable { $displayName }"
    }

    @AfterTest
    fun afterTest() {
        // Quietly free any disposables left over from the last test
        Disposer.freeRemaining { }
    }

    @Test
    fun testRegisterAndDispose() {
        val d1 = TestDisposable()
        val d2 = TestDisposable()

        assertThat(Disposer.isRegistered(d1)).isFalse()
        assertThat(Disposer.isRegistered(d2)).isFalse()

        val d1Box = Disposer.register(d1)
        val d2Box = Disposer.register(d2)

        assertThat(Disposer.isRegistered(d1)).isTrue()
        assertThat(Disposer.isRegistered(d2)).isTrue()
        assertThat(d1.disposed).isFalse()
        assertThat(d2.disposed).isFalse()

        Disposer.dispose(d2Box)

        assertThat(Disposer.isRegistered(d1)).isTrue()
        assertThat(Disposer.isRegistered(d2)).isFalse()
        assertThat(d1.disposed).isFalse()
        assertThat(d2.disposed).isTrue()

        Disposer.dispose(d1Box)

        assertThat(Disposer.isRegistered(d1)).isFalse()
        assertThat(Disposer.isRegistered(d2)).isFalse()
        assertThat(d1.disposed).isTrue()
        assertThat(d2.disposed).isTrue()
    }

    @Test
    fun reregistrationAllowedIfPossible() {
        val grandparent = TestDisposable()
        val parent = TestDisposable()
        val child = TestDisposable()

        Disposer.register(grandparent)
        Disposer.register(parent)
        Disposer.register(child)

        // No-op
        Disposer.register(grandparent)
        Disposer.register(parent)
        Disposer.register(child)

        // Change parent
        Disposer.register(grandparent, parent)
        Disposer.register(parent, child)

        assertThrows<IllegalDisposerOperation> {
            Disposer.register(child, grandparent)
        }
    }

    @Test
    fun canRegisterViaRawParent() {
        val parent = TestDisposable()
        val child = TestDisposable()

        val parentBox = Disposer.register(parent)
        val childBox = Disposer.register(parent, child)

        Disposer.dispose(parentBox)
        assertThat(childBox.disposed).isTrue()
    }

    @Test
    fun canReparentViaRawChild() {
        val parent1Box = Disposer.register(TestDisposable())
        val parent2Box = Disposer.register(TestDisposable())
        val child = TestDisposable()

        Disposer.register(child)
        Disposer.register(parent1Box, child)
        Disposer.register(parent2Box, child)

        Disposer.dispose(parent1Box)
        assertThat(child.disposed).isFalse()

        Disposer.dispose(parent2Box)
        assertThat(child.disposed).isTrue()

        // Should be a no-op. Everything was already freed!
        Disposer.freeRemaining { fail(it) }
    }

    @Test
    fun cannotDisposeAnAlreadyDisposedBox() {
        val box = Disposer.register(TestDisposable())
        Disposer.dispose(box)
        assertThrows<AlreadyDisposedException> {
            Disposer.dispose(box)
        }
    }

    @Test
    fun canDisposeRecursivelyViaParents() {
        val b1 = Disposer.register(TestDisposable())
        val b11 = Disposer.register(b1, TestDisposable())
        val b111 = Disposer.register(b11, TestDisposable())
        val b1111 = Disposer.register(b111, TestDisposable())
        val b2 = Disposer.register(TestDisposable())

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
        TestDisposable().let { d ->
            assertThat(Disposer.isRegistered(d)).isFalse()
            assertThat(d.disposed).isFalse()
            d.use {
                assertThat(d.disposed).isFalse()
                assertThat(Disposer.isRegistered(d)).isTrue()
            }
            assertThat(Disposer.isRegistered(d)).isFalse()
            assertThat(d.disposed).isTrue()
        }

        TestDisposable().use {
            assertThat(Disposer.isRegistered(it)).isTrue()
            assertThat(it.disposed).isFalse()
        }

        // use block still disposes even if an exception is thrown
        TestDisposable().let { d ->
            assertThrows<RuntimeException> {
                d.use {
                    throw RuntimeException()
                }
            }
            assertThat(d.disposed).isTrue()
            assertThat(Disposer.isRegistered(d)).isFalse()
        }
    }

    @Test
    fun canCreateDisposableViaConvenienceConstructionMethod() {
        var disposed = false
        val d = disposable { disposed = true }

        assertThat(disposed).isFalse()
        val box = Disposer.register(d)
        assertThat(disposed).isFalse()
        Disposer.dispose(box)
        assertThat(disposed).isTrue()
    }

    @Test
    fun canTransferOwnershipOfDisposables() {
        val parent1 = Disposer.register(TestDisposable())
        val parent2 = Disposer.register(TestDisposable())

        val child1 = Disposer.register(parent1, TestDisposable())
        val child2 = Disposer.register(parent1, TestDisposable())
        val child3 = Disposer.register(parent1, TestDisposable())

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
        val parent = Disposer.register(TestDisposable())

        val child1 = Disposer.register(parent, TestDisposable())
        val child2 = Disposer.register(parent, TestDisposable())
        val child3 = Disposer.register(parent, TestDisposable())

        Disposer.transferChildren(from = parent, to = parent)

        Disposer.dispose(parent)
        assertThat(child1.disposed).isTrue()
        assertThat(child2.disposed).isTrue()
        assertThat(child3.disposed).isTrue()
    }

    @Test
    fun transferringOwernshipToAChildDisposableThrowsAnException() {
        val parent = Disposer.register(TestDisposable())

        val child1 = Disposer.register(parent, TestDisposable())
        val child2 = Disposer.register(parent, TestDisposable())
        val child3 = Disposer.register(parent, TestDisposable())

        assertThrows<IllegalDisposerOperation> {
            Disposer.transferChildren(from = parent, to = child2)
        }
    }

    @Test
    fun testFreeRemainingMessage() {
        val b1 = Disposer.register(TestDisposable("b1"))
        val b11 = Disposer.register(b1, TestDisposable("b11"))
        val b12 = Disposer.register(b1, TestDisposable("b12"))
        val b121 = Disposer.register(b12, TestDisposable("b121"))
        val b13 = Disposer.register(b1, TestDisposable("b13"))
        val b131 = Disposer.register(b13, TestDisposable("b131"))
        val b132 = Disposer.register(b13, TestDisposable("b132"))

        val b2 = Disposer.register(TestDisposable("b2"))
        val b21 = Disposer.register(b2, TestDisposable("b21"))
        val b22 = Disposer.register(b2,TestDisposable("b22"))
        val b221 = Disposer.register(b22, TestDisposable("b221"))
        val b2211 = Disposer.register(b221, TestDisposable("b2211"))

        val b3 = Disposer.register(TestDisposable("b3"))
        val b31 = Disposer.register(b3, TestDisposable("b31"))

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

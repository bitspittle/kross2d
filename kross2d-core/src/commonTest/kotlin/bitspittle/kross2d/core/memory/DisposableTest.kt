package bitspittle.kross2d.core.memory

import bitspittle.truthish.assertThat
import bitspittle.truthish.assertThrows
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.fail

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

        Disposer.register(d1)
        Disposer.register(d2)

        assertThat(Disposer.isRegistered(d1)).isTrue()
        assertThat(Disposer.isRegistered(d2)).isTrue()
        assertThat(d1.disposed).isFalse()
        assertThat(d2.disposed).isFalse()

        Disposer.dispose(d2)

        assertThat(Disposer.isRegistered(d1)).isTrue()
        assertThat(Disposer.isRegistered(d2)).isFalse()
        assertThat(d1.disposed).isFalse()
        assertThat(d2.disposed).isTrue()

        Disposer.dispose(d1)

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

        assertThrows<IllegalArgumentException> {
            Disposer.register(child, grandparent)
        }
    }

    @Test
    fun disposingNonRegisteredDisposableThrowsException() {
        val d = TestDisposable()
        assertThrows<IllegalArgumentException> {
            Disposer.dispose(d)
        }
        Disposer.register(d)
        Disposer.dispose(d)
        assertThrows<IllegalArgumentException> {
            Disposer.dispose(d)
        }
    }

    @Test
    fun canDisposeRecursivelyViaParents() {
        val d1 = TestDisposable()
        val d11 = TestDisposable()
        val d111 = TestDisposable()
        val d1111 = TestDisposable()
        val d2 = TestDisposable()

        Disposer.register(d1)
        Disposer.register(d1, d11)
        Disposer.register(d11, d111)
        Disposer.register(d111, d1111)
        Disposer.register(d2)

        Disposer.dispose(d111)

        assertThat(d1.disposed).isFalse()
        assertThat(d11.disposed).isFalse()
        assertThat(d111.disposed).isTrue()
        assertThat(d1111.disposed).isTrue()
        assertThat(d2.disposed).isFalse()

        Disposer.dispose(d1)
        assertThat(d1.disposed).isTrue()
        assertThat(d11.disposed).isTrue()
        assertThat(d2.disposed).isFalse()

        Disposer.dispose(d2)
        assertThat(d2.disposed).isTrue()
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
        Disposer.register(d)
        assertThat(disposed).isFalse()
        Disposer.dispose(d)
        assertThat(disposed).isTrue()
    }

    @Test
    fun canTransferOwnershipOfDisposables() {
        val parent1 = TestDisposable()
        val parent2 = TestDisposable()

        val child1 = TestDisposable()
        val child2 = TestDisposable()
        val child3 = TestDisposable()

        Disposer.register(parent1)
        Disposer.register(parent1, child1)
        Disposer.register(parent1, child2)
        Disposer.register(parent1, child3)
        Disposer.register(parent2)

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

        val child1 = TestDisposable()
        val child2 = TestDisposable()
        val child3 = TestDisposable()

        Disposer.register(parent)
        Disposer.register(parent, child1)
        Disposer.register(parent, child2)
        Disposer.register(parent, child3)

        Disposer.transferChildren(from = parent, to = parent)

        Disposer.dispose(parent)
        assertThat(child1.disposed).isTrue()
        assertThat(child2.disposed).isTrue()
        assertThat(child3.disposed).isTrue()
    }

    @Test
    fun transferringOwernshipToAChildDisposableThrowsAnException() {
        val parent = TestDisposable()

        val child1 = TestDisposable()
        val child2 = TestDisposable()
        val child3 = TestDisposable()

        Disposer.register(parent)
        Disposer.register(parent, child1)
        Disposer.register(parent, child2)
        Disposer.register(parent, child3)

        assertThrows<IllegalArgumentException> {
            Disposer.transferChildren(from = parent, to = child2)
        }
    }

    @Test
    fun testFreeRemainingMessage() {
        val d1 = TestDisposable("d1")
        val d11 = TestDisposable("d11")
        val d12 = TestDisposable("d12")
        val d121 = TestDisposable("d121")
        val d13 = TestDisposable("d13")
        val d131 = TestDisposable("d131")
        val d132 = TestDisposable("d132")
        val d2 = TestDisposable("d2")
        val d21 = TestDisposable("d21")
        val d22 = TestDisposable("d22")
        val d221 = TestDisposable("d221")
        val d2211 = TestDisposable("d2211")
        val d3 = TestDisposable("d3")
        val d31 = TestDisposable("d31")

        Disposer.register(d1)
        Disposer.register(d1, d11)
        Disposer.register(d1, d12)
        Disposer.register(d12, d121)
        Disposer.register(d1, d13)
        Disposer.register(d13, d131)
        Disposer.register(d13, d132)

        Disposer.register(d2)
        Disposer.register(d2, d21)
        Disposer.register(d2, d22)
        Disposer.register(d22, d221)
        Disposer.register(d221, d2211)

        Disposer.register(d3)
        Disposer.register(d3, d31)

        Disposer.freeRemaining {
            assertThat(it.trim()).isEqualTo(
                """
                Some disposables were not cleaned up:

                TestDisposable { d1 }
                  TestDisposable { d11 }
                  TestDisposable { d12 }
                    TestDisposable { d121 }
                  TestDisposable { d13 }
                    TestDisposable { d131 }
                    TestDisposable { d132 }

                TestDisposable { d2 }
                  TestDisposable { d21 }
                  TestDisposable { d22 }
                    TestDisposable { d221 }
                      TestDisposable { d2211 }

                TestDisposable { d3 }
                  TestDisposable { d31 }
                """.trimIndent()
            )
        }

        // Second call should be a no-op. Everything was already freed!
        Disposer.freeRemaining { fail(it) }
    }
}
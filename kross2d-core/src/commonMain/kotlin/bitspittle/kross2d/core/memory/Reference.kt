package bitspittle.kross2d.core.memory

interface Reference<out T> {
    fun deref(): T
}

/**
 * Convenience function for handling the de-referenced value in a block.
 *
 * This is essentially a shortcut for `ref.deref().let { ... }`
 */
inline fun <T, R> Reference<T>.deref(block: (T) -> R): R {
    return block(deref())
}

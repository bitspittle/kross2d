package dev.bitspittle.kross2d.core.concurrency

/**
 * A common multiplatform version of JVM's `kotlin.synchronized` method.
 *
 * This will ensure that multiple calls to any synchronized function with the same target lock will not run in parallel
 * but instead sequentially, blocking as necessary to allow this to happen.
 *
 * Do use it, pass in anything consistent as a lock object, which can just be a dummy `Any` instance:
 *
 * ```
 * private val lock = Any()
 *
 * fun doSomethingSync() = synchronized(lock) { ... }
 * ```
 */
internal expect inline fun <T> synchronized(lock: Any, block: () -> T): T
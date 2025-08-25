package dev.bitspittle.kross2d.core.concurrency

// No need to use the lock on JS as there's only ever one thread
internal actual inline fun <T> synchronized(lock: Any, block: () -> T): T = block()
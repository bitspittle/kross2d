package dev.bitspittle.kross2d.core.concurrency

internal actual inline fun <T> synchronized(lock: Any, block: () -> T): T = kotlin.synchronized(lock, block)
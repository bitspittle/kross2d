package dev.bitspittle.kross2d.core.time

import kotlin.js.Date

internal actual fun nowNs(): Long {
    // Nanosecond accuracy is not readily available on browsers (for security?). We'll just have
    // to accept that milliseconds are good enough.
    val nowMs = Date.now()
    val nowNs = nowMs * 1_000_000
    return nowNs.toLong()
}
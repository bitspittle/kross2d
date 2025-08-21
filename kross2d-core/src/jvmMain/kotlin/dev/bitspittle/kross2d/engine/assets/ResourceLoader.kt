package dev.bitspittle.kross2d.engine.assets

import java.io.InputStream
import java.net.URL

class ResourceLoader(private val root: String) {
    fun load(relativePath: String): ByteArray? {
        return stream(relativePath)?.use { it.readBytes() }
    }

    fun stream(relativePath: String): InputStream? {
        return this.javaClass.classLoader.getResourceAsStream("$root/$relativePath")
    }

    fun url(relativePath: String): URL? {
        return this.javaClass.classLoader.getResource("$root/$relativePath")
    }
}

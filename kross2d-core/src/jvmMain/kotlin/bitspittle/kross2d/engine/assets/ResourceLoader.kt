package bitspittle.kross2d.engine.assets

import java.io.InputStream

class ResourceLoader(private val root: String) {
    fun load(relativePath: String): ByteArray? {
        val resource: InputStream? = this.javaClass.classLoader.getResourceAsStream("$root/$relativePath")
        return resource?.use { return it.readBytes() }
    }
}

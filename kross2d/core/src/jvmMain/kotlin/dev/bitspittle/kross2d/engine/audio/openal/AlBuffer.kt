package dev.bitspittle.kross2d.engine.audio.openal

import dev.bitspittle.kross2d.core.memory.Disposable
import com.jogamp.openal.ALFactory
import java.nio.ByteBuffer

class AlBuffer: Disposable() {
    val id: Int
    private val idPtr: IntArray

    init {
        val al = ALFactory.getAL()

        val bufferIdOut = IntArray(1)
        al.alGenBuffers(1, bufferIdOut, 0)
        al.throwIfError("Could not allocate audio buffer")

        id = bufferIdOut[0]
        idPtr = intArrayOf(id)
    }

    fun setData(data: ByteBuffer, size: Int, format: Int, freq: Int) {
        val al = ALFactory.getAL()
        al.alBufferData(id, format, data, size, freq)
        al.throwIfError("Unable to set buffer data")
    }

    fun enqueueOnto(source: AlSource) {
        val al = ALFactory.getAL()
        al.alSourceQueueBuffers(source.id, 1, idPtr, 0)
        al.throwIfError("Unable to queue buffer")
    }

    fun dequeueFrom(source: AlSource) {
        val al = ALFactory.getAL()
        al.alSourceUnqueueBuffers(source.id, 1, idPtr, 0)
        al.throwIfError("Unable to unenqueue buffer")
    }

    override fun onDisposed() {
        val al = ALFactory.getAL()

        al.alDeleteBuffers(1, idPtr, 0)
        al.throwIfError("Could not deallocate audio buffer")
    }
}


package bitspittle.kross2d.engine.audio.ogg

import bitspittle.kross2d.engine.audio.AlSoundBuffer
import bitspittle.kross2d.engine.audio.AlSoundSource
import bitspittle.kross2d.engine.audio.throwIfError
import com.jogamp.openal.AL
import com.jogamp.openal.ALFactory
import com.jogamp.openal.util.ALut
import java.nio.ByteBuffer

class OggBuffer(private val source: AlSoundSource, private val format: Int, private val rate: Int): AlSoundBuffer {
    override var id: Int = 0
        private set

    init {
        val al = ALFactory.getAL()

        val bufferIdOut = IntArray(1)
        al.alGenBuffers(1, bufferIdOut, 0)
        al.throwIfError("Could not allocate audio buffer")

        id = bufferIdOut[0]
    }

    fun setData(pcm: ByteArray, size: Int) {
        val data = ByteBuffer.wrap(pcm, 0, size)
        val al = ALFactory.getAL()
        al.alBufferData(id, format, data, size, rate)
        al.throwIfError("Unable to set buffer data")
    }

    fun enqueue() {
        val al = ALFactory.getAL()
        val bufferId = intArrayOf(id)
        al.alSourceQueueBuffers(source.id, 1, bufferId, 0)
        al.throwIfError("Unable to queue buffer")
    }

    fun dequeue() {
        val al = ALFactory.getAL()
        val bufferId = intArrayOf(id)
        al.alSourceUnqueueBuffers(source.id, 1, bufferId, 0)
        al.throwIfError("Unable to unenqueue buffer")
    }

    override fun dispose() {
        val al = ALFactory.getAL()

        al.alDeleteBuffers(1, IntArray(1) { id }, 0)
        al.throwIfError("Could not deallocate audio buffer")
        id = -1
    }
}

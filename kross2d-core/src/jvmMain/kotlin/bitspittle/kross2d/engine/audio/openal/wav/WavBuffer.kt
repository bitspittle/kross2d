package bitspittle.kross2d.engine.audio.openal.wav

import bitspittle.kross2d.engine.audio.openal.AlBuffer
import bitspittle.kross2d.engine.audio.openal.throwIfError
import com.jogamp.openal.ALFactory
import com.jogamp.openal.util.ALut
import java.io.InputStream
import java.nio.ByteBuffer

class WavBuffer(stream: InputStream): AlBuffer {
    override var id: Int = 0
        private set

    val format: Int
    val size: Int
    val data: ByteBuffer
    val freq: Int
    val loop: Int

    init {
        val al = ALFactory.getAL()

        val idOut = IntArray(1)
        al.alGenBuffers(1, idOut, 0)
        al.throwIfError("Could not allocate audio buffer")

        id = idOut[0]

        val formatOut = IntArray(1)
        val sizeOut = IntArray(1)
        val dataOut = arrayOfNulls<ByteBuffer>(1)
        val freqOut = IntArray(1)
        val loopOut = IntArray(1)

        ALut.alutLoadWAVFile(stream, formatOut, dataOut, sizeOut, freqOut, loopOut)

        format = formatOut[0]
        data = dataOut[0]!!
        size = sizeOut[0]
        freq = freqOut[0]
        loop = loopOut[0]

        al.alBufferData(id, format, data, size, freq)
    }

    override fun dispose() {
        val al = ALFactory.getAL()
        al.alDeleteBuffers(1, IntArray(1) { id }, 0)
        id = -1
    }
}
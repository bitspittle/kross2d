package bitspittle.kross2d.engine.audio.openal.wav

import bitspittle.kross2d.core.memory.Box
import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.Disposer
import bitspittle.kross2d.engine.audio.openal.AlBuffer
import com.jogamp.openal.ALFactory
import com.jogamp.openal.util.ALut
import java.io.InputStream
import java.nio.ByteBuffer

class WavData(stream: InputStream): Disposable {
    val alBuffer: Box<AlBuffer>

    init {
        val formatPtr = IntArray(1)
        val sizePtr = IntArray(1)
        val dataPtr = arrayOfNulls<ByteBuffer>(1)
        val freqPtr = IntArray(1)
        val loopPtr = IntArray(1)

        ALut.alutLoadWAVFile(stream, formatPtr, dataPtr, sizePtr, freqPtr, loopPtr)

        val format = formatPtr[0]
        val data = dataPtr[0]!!
        val size = sizePtr[0]
        val freq = freqPtr[0]

        val buffer = AlBuffer()
        buffer.setData(data, size, format, freq)
        alBuffer = Disposer.register(this, buffer)
    }
}
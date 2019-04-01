package bitspittle.kross2d.engine.audio

import com.jogamp.openal.AL
import com.jogamp.openal.ALFactory
import com.jogamp.openal.util.ALut
import java.io.InputStream
import java.nio.ByteBuffer

class AlException(msg: String, alError: Int) : Exception("$msg [AL err code: $alError]")
fun AL.throwIfError(msg: String) {
    alGetError().let {
    alError ->
        if (alError != AL.AL_NO_ERROR) {
            throw AlException(msg, alError)
        }
    }
}

class AlGlobalState {
    val listenerPos = floatArrayOf(0.0f, 0.0f, 0.0f)
    val listenerVel = floatArrayOf(0.0f, 0.0f, 0.0f)
    val listenerOrientation = floatArrayOf(0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f)

    init {
        ALut.alutInit()
        val al = ALFactory.getAL()
        al.throwIfError("Could not initialize OpenAL")

        al.alListenerfv(AL.AL_POSITION, listenerPos, 0)
        al.alListenerfv(AL.AL_VELOCITY, listenerVel, 0)
        al.alListenerfv(AL.AL_ORIENTATION, listenerOrientation, 0)

        Runtime.getRuntime().addShutdownHook(Thread { dispose() })
    }

    fun dispose() {
        ALut.alutExit()
    }
}

class AlSoundBuffer(stream: InputStream) {
    val bufferId: Int
    val format: Int
    val size: Int
    val data: ByteBuffer
    val freq: Int
    val loop: Int

    init {
        val al = ALFactory.getAL()

        val bufferIdOut = IntArray(1)
        al.alGenBuffers(1, bufferIdOut, 0)
        al.throwIfError("Could not allocate audio buffer")

        bufferId = bufferIdOut[0]

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

        al.alBufferData(bufferId, format, data, size, freq)
    }

    fun dispose() {
        val al = ALFactory.getAL()
        al.alDeleteBuffers(1, IntArray(1) { bufferId }, 0)
    }
}

class AlSoundSource {
    val sourceId: Int
    val sourcePos = floatArrayOf(0.0f, 0.0f, 0.0f)
    val sourceVel = floatArrayOf(0.0f, 0.0f, 0.0f)

    init {
        val al = ALFactory.getAL()

        val sourceIdOut = IntArray(1)
        al.alGenSources(1, sourceIdOut, 0)
        al.throwIfError("Could not allocate audio source")

        sourceId = sourceIdOut[0]
    }

    fun attachToBuffer(alBuffer: AlSoundBuffer) {
        val al = ALFactory.getAL()

        al.alSourcei(sourceId, AL.AL_BUFFER, alBuffer.bufferId)
        al.alSourcef(sourceId, AL.AL_PITCH, 1.0f)
        al.alSourcef(sourceId, AL.AL_GAIN, 1.0f)
        al.alSourcefv(sourceId, AL.AL_POSITION, sourcePos, 0)
        al.alSourcefv(sourceId, AL.AL_VELOCITY, sourceVel, 0)
        al.alSourcei(sourceId, AL.AL_LOOPING, alBuffer.loop)
    }

    fun dispose() {
        val al = ALFactory.getAL()
        al.alDeleteSources(1, IntArray(1) { sourceId }, 0)
    }
}



actual class Sound(stream: InputStream) {
    companion object {
        fun tryCreate(stream: InputStream): Sound? {
            return try {
                Sound(stream)
            } catch (_: AlException) {
                null
            }
        }

        private val AL_GLOBAL_STATE = AlGlobalState()
    }

    val audioBuffer = AlSoundBuffer(stream)
    val audioSource = AlSoundSource()

    init {
        audioSource.attachToBuffer(audioBuffer)
    }

    actual fun play() {
        val al = ALFactory.getAL()
        al.alSourceStop(audioSource.sourceId)
        al.alSourcePlay(audioSource.sourceId)
    }

    fun dispose() {
        audioBuffer.dispose()
        audioSource.dispose()
    }
}
package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.Box
import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.Disposer
import bitspittle.kross2d.core.memory.Rc
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

class AlGlobalState: Disposable {
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
    }

    override fun dispose() {
        ALut.alutExit()
    }
}

class AlSoundBuffer(stream: InputStream): Disposable {
    var bufferId: Int
        private set
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

    override fun dispose() {
        val al = ALFactory.getAL()
        al.alDeleteBuffers(1, IntArray(1) { bufferId }, 0)
        bufferId = -1
    }
}

class AlSoundSource: Disposable {
    var sourceId: Int
        private set

    private val sourcePos = floatArrayOf(0.0f, 0.0f, 0.0f)
    private val sourceVel = floatArrayOf(0.0f, 0.0f, 0.0f)

    init {
        val al = ALFactory.getAL()

        val sourceIdOut = IntArray(1)
        al.alGenSources(1, sourceIdOut, 0)
        al.throwIfError("Could not allocate audio source")

        sourceId = sourceIdOut[0]
    }

    fun attachToBuffer(alBuffer: Box<AlSoundBuffer>) {
        val al = ALFactory.getAL()

        @Suppress("NAME_SHADOWING")
        alBuffer.deref { alBuffer ->
            al.alSourcei(sourceId, AL.AL_BUFFER, alBuffer.bufferId)
            al.alSourcef(sourceId, AL.AL_PITCH, 1.0f)
            al.alSourcef(sourceId, AL.AL_GAIN, 1.0f)
            al.alSourcefv(sourceId, AL.AL_POSITION, sourcePos, 0)
            al.alSourcefv(sourceId, AL.AL_VELOCITY, sourceVel, 0)
            al.alSourcei(sourceId, AL.AL_LOOPING, alBuffer.loop)
        }
    }

    override fun dispose() {
        val al = ALFactory.getAL()
        al.alDeleteSources(1, IntArray(1) { sourceId }, 0)
        sourceId = -1
    }
}

actual class SoundHandle(buffer: Box<AlSoundBuffer>): Disposable {
    private val audioSource = AlSoundSource()
        .apply { attachToBuffer(buffer) }
        .also { source -> Disposer.register(this, source) }

    private val al = ALFactory.getAL()
    private val isValid
        get() = audioSource.sourceId >= 0 // Should be true until disposed

    fun play() {
        if (isValid) {
            al.alSourcePlay(audioSource.sourceId)
        }
    }

    fun stop() {
        if (isValid) {
            al.alSourceStop(audioSource.sourceId)
        }
    }

    fun pause() {
        if (isValid) {
            al.alSourcePause(audioSource.sourceId)
        }
    }

    fun isStopped(): Boolean {
        if (!isValid) return true

        val stateOut = IntArray(1)
        al.alGetSourcei(audioSource.sourceId, AL.AL_SOURCE_STATE, stateOut, 0)
        return stateOut[0] == AL.AL_STOPPED
    }
}

actual class Sound(stream: InputStream) : Disposable {
    companion object {
        fun tryCreate(stream: InputStream): Sound? {
            return try {
                Sound(stream)
            } catch (_: AlException) {
                null
            }
        }

        private val audioGlobalState = Rc { AlGlobalState() }
    }

    private val audioBuffer: Box<AlSoundBuffer>
    private val handles = mutableListOf<Box<SoundHandle>>()

    init {
        audioGlobalState.inc()

        audioBuffer = Disposer.register(this, AlSoundBuffer(stream))
    }

    actual fun play(): Box<SoundHandle> {
        disposeStoppedSounds()
        Disposer.register(audioBuffer, SoundHandle(audioBuffer)).let { soundHandle ->
            handles.add(soundHandle)
            soundHandle.deref().play()
            return soundHandle
        }
    }

    actual fun stop(handle: Box<SoundHandle>?) {
        handles.forEach { if (handle == null || handle === it) it.deref().stop() }
        disposeStoppedSounds()
    }

    actual fun pause(handle: Box<SoundHandle>?) {
        disposeStoppedSounds()
        handles.forEach { if (handle == null || handle === it) it.deref().pause() }
    }

    actual fun resume(handle: Box<SoundHandle>?) {
        disposeStoppedSounds()
        handles.forEach { if (handle == null || handle === it) it.deref().play() }
    }

    /**
     * OpenAL doesn't have an easy way to get notified when a sound stops on its own (vs. the
     * caller stopping them manually), so we just lazily remove them any time the user interacts
     * with our API.
     */
    private fun disposeStoppedSounds() {
        handles.forEach { if (it.deref().isStopped()) Disposer.dispose(it) }
        handles.removeAll { it.disposed }
    }

    override fun dispose() {
        audioGlobalState.dec()
    }
}
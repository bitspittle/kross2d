package bitspittle.kross2d.engine.audio

import bitspittle.kross2d.core.memory.Box
import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.Rc
import bitspittle.kross2d.core.memory.deref
import com.jogamp.openal.AL
import com.jogamp.openal.ALFactory
import com.jogamp.openal.util.ALut

/**
 * This file contains OpenAL-related classes shared by both [Sound] and [Music] classes.
 */

/**
 * Exception that wraps an [AL] error code.
 */
class AlException(msg: String, alError: Int) : Exception("$msg [AL err code: $alError]")
fun AL.throwIfError(msg: String) {
    alGetError().let { alError ->
        if (alError != AL.AL_NO_ERROR) {
            throw AlException(msg, alError)
        }
    }
}

/**
 * OpenAL global state / singletons, e.g. the listener's position, global init/exit calls, etc.
 */
class AlGlobalState: Disposable {
    companion object {
        internal val INSTANCE = Rc { AlGlobalState() }
    }

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

interface AlSoundBuffer: Disposable {
    val id: Int
}


class AlSoundSource: Disposable {
    var id: Int
        private set

    val isPlaying: Boolean
        get() {
            val stateOut = IntArray(1)
            val al = ALFactory.getAL()
            al.alGetSourcei(id, AL.AL_SOURCE_STATE, stateOut, 0)
            return stateOut[0] == AL.AL_PLAYING
        }

    val isPaused: Boolean
        get() {
            val stateOut = IntArray(1)
            val al = ALFactory.getAL()
            al.alGetSourcei(id, AL.AL_SOURCE_STATE, stateOut, 0)
            return stateOut[0] == AL.AL_PAUSED
        }

    private val pos = floatArrayOf(0.0f, 0.0f, 0.0f)
    private val vel = floatArrayOf(0.0f, 0.0f, 0.0f)
    private val dir = floatArrayOf(0.0f, 0.0f, 0.0f)

    init {
        val al = ALFactory.getAL()

        val idOut = IntArray(1)
        al.alGenSources(1, idOut, 0)
        al.throwIfError("Could not allocate audio source")

        id = idOut[0]
    }

    fun attachToBuffer(buffer: AlSoundBuffer) {
        val al = ALFactory.getAL()

        al.alSourcei(id, AL.AL_BUFFER, buffer.id)
        al.alSourcef(id, AL.AL_PITCH, 1.0f)
        al.alSourcef(id, AL.AL_GAIN, 1.0f)
        al.alSourcef(id, AL.AL_ROLLOFF_FACTOR, 0.0f)

        al.alSourcei(id, AL.AL_SOURCE_RELATIVE, AL.AL_TRUE)
        al.alSourcefv(id, AL.AL_POSITION, pos, 0)
        al.alSourcefv(id, AL.AL_VELOCITY, vel, 0)
        al.alSourcefv(id, AL.AL_DIRECTION, dir, 0)
    }

    fun play() {
        val al = ALFactory.getAL()
        al.alSourcePlay(id)
    }

    fun stop() {
        val al = ALFactory.getAL()
        al.alSourceStop(id)
    }

    fun pause() {
        val al = ALFactory.getAL()
        al.alSourcePause(id)
    }

    override fun dispose() {
        val al = ALFactory.getAL()
        al.alDeleteSources(1, intArrayOf(id), 0)
        id = -1
    }
}
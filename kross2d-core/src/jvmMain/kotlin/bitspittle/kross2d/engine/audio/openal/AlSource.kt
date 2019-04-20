package bitspittle.kross2d.engine.audio.openal

import bitspittle.kross2d.core.memory.Disposable
import com.jogamp.openal.AL
import com.jogamp.openal.ALFactory

/**
 * This file contains OpenAL-related classes shared by both [Sound] and [Music] classes.
 */


class AlSource: Disposable {
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

    fun attachToBuffer(buffer: AlBuffer) {
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
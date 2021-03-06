package bitspittle.kross2d.engine.audio.openal

import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.Rc
import com.jogamp.openal.AL
import com.jogamp.openal.ALFactory
import com.jogamp.openal.util.ALut

/**
 * OpenAL global state / singletons, e.g. the listener's position, global init/exit calls, etc.
 */
class AlGlobalState private constructor(): Disposable() {
    companion object {
        internal val INSTANCE =
            Rc { AlGlobalState() }
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

    override fun onDisposed() {
        ALut.alutExit()
    }
}
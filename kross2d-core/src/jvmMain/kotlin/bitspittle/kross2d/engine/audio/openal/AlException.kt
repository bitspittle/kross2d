package bitspittle.kross2d.engine.audio.openal

import com.jogamp.openal.AL

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

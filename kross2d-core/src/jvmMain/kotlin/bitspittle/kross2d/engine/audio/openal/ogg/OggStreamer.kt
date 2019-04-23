package bitspittle.kross2d.engine.audio.openal.ogg

import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.Disposer
import bitspittle.kross2d.core.memory.setParent
import bitspittle.kross2d.engine.audio.openal.Stream
import com.jogamp.openal.AL
import kotlinx.coroutines.delay
import java.net.URL
import java.nio.ByteBuffer

/**
 *
 * This is a translation of the OggVorbis streamer OpenAL tutorial
 * at http://www.devmaster.net/articles/openal-tutorials/lesson8.php
 *
 * It uses the Java Ogg library from http://www.j-ogg.de to do the Ogg
 * file decoding...
 *
 * @author Krishna K Gadepalli
 */
class OggStreamer(url: URL): Disposable() {
    companion object {
        // The size of a chunk from the stream that we want to read for each update.
        private var BUFFER_SIZE = 4096 * 16
    }

    private val oggDecoder: OggDecoder = OggDecoder(url).setParent(this)

    private val stream: Stream
    private val sleepTimeMs: Long = 10

    init {
        val format = when (oggDecoder.numChannels) {
            1 -> AL.AL_FORMAT_MONO16
            else -> AL.AL_FORMAT_STEREO16
        }

        stream = Stream(format, oggDecoder.sampleRate) {
            val pcm = ByteArray(BUFFER_SIZE)
            val size = oggDecoder.readChunkInto(pcm)
            if (size > 0) Stream.Packet(ByteBuffer.wrap(pcm, 0, size), size) else null
        }

        stream.setParent(this)
    }

    /**
     * The main loop to initialize and play the entire stream
     */
    suspend fun playLoop() {
        if (stream.start()) {
            while (stream.update()) {
                // We will try sleeping for sometime so that we don't peg the CPU...
                delay(sleepTimeMs)
            }
        }
    }

    fun stop() {
        stream.stop()
    }

    fun pause() {
        stream.pause()
    }

    fun resume() {
        stream.resume()
    }
}
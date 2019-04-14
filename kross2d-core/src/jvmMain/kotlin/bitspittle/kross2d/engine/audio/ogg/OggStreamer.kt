package bitspittle.kross2d.engine.audio.ogg

import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.core.memory.Disposer
import bitspittle.kross2d.core.memory.disposable
import bitspittle.kross2d.engine.audio.AlSoundSource
import com.jogamp.openal.AL
import com.jogamp.openal.ALFactory
import kotlinx.coroutines.delay
import java.net.URL

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
class OggStreamer(url: URL): Disposable {
    companion object {
        // The size of a chunk from the stream that we want to read for each update.
        private var BUFFER_SIZE = 4096 * 16

        // The number of buffers used in the audio pipeline
        private var NUM_BUFFERS = 2
    }

    private val oggDecoder: OggDecoder = OggDecoder(url)

    /**
     * Buffers that hold streaming data. There will be two buffers - so at least one will always be
     * written to while the other is read from.
     */
    private val buffers: Array<OggBuffer>
    private val source = AlSoundSource()
    private val sleepTimeMs: Long = 10

    private var currBuffer = 0

    init {
        val format = when (oggDecoder.numChannels) {
            1 -> AL.AL_FORMAT_MONO16
            else -> AL.AL_FORMAT_STEREO16
        }

        Disposer.register(this, disposable {
            // In order to dispose buffers, we need to make sure the source they are queued up
            // against is stopped and dequeued
            source.stop()

            val al = ALFactory.getAL()
            val queuedOut = intArrayOf(0)
            al.alGetSourcei(source.id, AL.AL_BUFFERS_QUEUED, queuedOut, 0)
            val queuedCount = queuedOut[0]
            if (queuedCount > 0) {
                al.alSourceUnqueueBuffers(source.id, queuedCount, IntArray(queuedCount) { 0 }, 0)
            }
        })
        buffers = Array(2) { OggBuffer(source, format, oggDecoder.sampleRate) }
        buffers.forEach { buffer -> Disposer.register(this, buffer) }
        Disposer.register(this, source)
    }

    private fun initialLoad(): Boolean {
        for (i in 0 until NUM_BUFFERS) {
            if (!loadNextChunkInto(buffers[i]))
                return false
        }

        buffers.forEach { it.enqueue() }
        return true
    }

    private fun updateBuffers(): Boolean {
        val processedOut = IntArray(1)
        var isActive = true

        val al = ALFactory.getAL()
        al.alGetSourcei(source.id, AL.AL_BUFFERS_PROCESSED, processedOut, 0)

        var processed = processedOut[0]
        while (processed > 0) {
            val buffer = buffers[currBuffer]
            buffer.dequeue()
            isActive = loadNextChunkInto(buffer)
            buffer.enqueue()
            currBuffer = (currBuffer + 1) % NUM_BUFFERS

            processed--
        }

        return isActive
    }

    private fun loadNextChunkInto(buffer: OggBuffer): Boolean {
        val pcm = ByteArray(BUFFER_SIZE)
        val size = oggDecoder.readChunkInto(pcm)
        if (size <= 0) return false

        buffer.setData(pcm, size)
        return true
    }

    /**
     * The main loop to initialize and play the entire stream
     */
    suspend fun playLoop() {
        if (!initialLoad())
            return

        source.play()
        while (source.isPaused || (source.isPlaying && updateBuffers())) {
            // We will try sleeping for sometime so that we dont
            // peg the CPU...
            delay(sleepTimeMs)
        }
    }

    fun stop() {
        source.stop()
    }

    fun pause() {
        source.pause()
    }

    fun resume() {
        source.play()
    }
}
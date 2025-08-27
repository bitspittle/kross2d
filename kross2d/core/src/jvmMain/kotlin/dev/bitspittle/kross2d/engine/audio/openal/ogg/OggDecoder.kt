package dev.bitspittle.kross2d.engine.audio.openal.ogg

import de.jarnbjo.ogg.CachedUrlStream
import de.jarnbjo.ogg.EndOfOggStreamException
import de.jarnbjo.ogg.LogicalOggStream
import de.jarnbjo.vorbis.IdentificationHeader
import de.jarnbjo.vorbis.VorbisStream
import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.Disposer
import java.io.InputStream
import java.net.URL
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioInputStream

/**
 * Class responsible for loading a target [Ogg](https://en.wikipedia.org/wiki/Ogg)
 * [Vorbis](https://en.wikipedia.org/wiki/Vorbis) file, decoding it, and reading chunks of it into
 * external [ByteArray] buffers that can be used by OpenAL.
 *
 * To use this, instantiate it and then repeatedly call [readChunkInto] until it returns 0. If you
 * need to start from the beginning, just [Disposer.dispose] this instance and create a new one.
 *
 * See also: [JOAL tutorials, lesson 8](https://jogamp.org/joal-demos/www/devmaster/lesson8.html)
 */
internal class OggDecoder(url: URL): Disposable() {
    companion object {
        // Resolves endian issues, apparently. TODO: Test on Win, Linux
        fun swapBytes(b: ByteArray, off: Int, len: Int) {
            for (i in off until (off + len) step 2) {
                b[i] = b[i + 1].also { b[i + 1] = b[i] }
            }
        }
    }

    private val audioInputStream: AudioInputStream
    private val streamHeader: IdentificationHeader

    private val audioFormat: AudioFormat

    private var streamEnded = false

    init {
        val urlStream = CachedUrlStream(url)

        val oggStream = urlStream.logicalStreams.iterator().next() as LogicalOggStream
        val vorbisStream = VorbisStream(oggStream)
        streamHeader = vorbisStream.identificationHeader

        audioFormat = AudioFormat(
            streamHeader.sampleRate.toFloat(),
            16,
            streamHeader.channels,
            true,
            true
        )

        audioInputStream = AudioInputStream(
            VorbisInputStream(vorbisStream), audioFormat, -1
        )
    }

    override fun onDisposed() {
        audioInputStream.close()
    }

    val numChannels = streamHeader.channels
    val sampleRate = streamHeader.sampleRate

    /**
     * Read the next chunk of data, decoded, into the target [buffer].
     *
     * This method returns the number of bytes actually read, where 0
     * indicates you're already at the end of the stream.
     */
    fun readChunkInto(buffer: ByteArray): Int {
        if (streamEnded)
            return 0

        var bytesRead = 0

        while (bytesRead < buffer.size) {
            val cnt = audioInputStream.read(buffer, bytesRead, buffer.size - bytesRead)
            if (cnt <= 0) {
                streamEnded = true
                break
            }
            bytesRead += cnt
        }

        swapBytes(buffer, 0, bytesRead)
        return bytesRead
    }

    private class VorbisInputStream(private val source: VorbisStream) : InputStream() {
        override fun read(): Int {
            throw NotImplementedError()
        }

        override fun read(buffer: ByteArray): Int {
            return read(buffer, 0, buffer.size)
        }

        override fun read(buffer: ByteArray, offset: Int, length: Int): Int {
            return try {
                source.readPcm(buffer, offset, length)
            } catch (e: EndOfOggStreamException) {
                -1
            }

        }
    }
}
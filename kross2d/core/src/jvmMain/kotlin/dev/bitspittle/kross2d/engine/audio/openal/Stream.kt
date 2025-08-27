package dev.bitspittle.kross2d.engine.audio.openal

import com.jogamp.openal.AL
import com.jogamp.openal.ALFactory
import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.core.memory.Disposer
import dev.bitspittle.kross2d.core.memory.register
import dev.bitspittle.kross2d.core.memory.setParent
import java.nio.ByteBuffer

internal typealias DataProvider = () -> Stream.Packet?

/**
 * A stream is a class that owns an [AlSource] and a queue of [AlBuffer]s.
 *
 * Streams in OpenAL work by using a source (responsible for playing the stream) along with two or
 * more dedicated buffers attached to it. (Note that this is somewhat opposite the case where a
 * sound can be loaded entirely into memory, which is where you have one buffer and one-to-many
 * sources attached to it)
 *
 * As there are several buffers, one can be locked (and read from) while the others can be
 * written to.
 *
 * Users of this class are responsible for providing a callback that provides a [Packet] of data
 * with the next bytes of data to load in.
 *
 * Once a stream is initialized, call [start] to initialize it and [update] regularly (every 10ms
 * or so) to cause it to continue to stream and play. You'll usually do this on a background
 * thread, something like:
 *
 * ```
 * start()
 * while(update()) {
 *   sleep(10ms);
 * }
 * ```
 *
 * If you want to restart the song, call [stop] then [start] again.
 */
internal class Stream(private val format: Int, private val freq: Int, private val dataProvider: DataProvider): Disposable() {
    class Packet(val data: ByteBuffer, val size: Int)

    private val alSource = AlSource()

    /**
     * At any time, one buffer will be used for reading and one for writing.
     * The index for the actively read buffer is indicated by [currBuffer]
     */
    private val alBuffers = Array(2) { AlBuffer() }
    private var currBuffer = -1

    init {
        // In order to allow buffers to properly dispose, we need to make sure the source they
        // are queued up against is stopped and dequeued (in that order)
        Disposer.register(this) { stop() }
        alSource.setParent(this)
        alBuffers.forEach { it.setParent(this) }
    }

    fun start(): Boolean {
        if (currBuffer < 0) {
            if (initializeBuffers()) {
                currBuffer = 0
                alSource.play()
                return true
            }
        }

        return false
    }

    fun update(): Boolean {
        if (currBuffer < 0) return false

        if (!alSource.isPaused) {
            val processedCountPtr = IntArray(1)

            val al = ALFactory.getAL()
            al.alGetSourcei(alSource.id, AL.AL_BUFFERS_PROCESSED, processedCountPtr, 0)

            var processedCount = processedCountPtr[0]
            while (processedCount > 0) {
                val buffer = alBuffers[currBuffer]
                buffer.dequeueFrom(alSource)

                val packet = dataProvider()
                if (packet != null) {
                    buffer.setDataAndEnqueue(packet)
                    currBuffer = (currBuffer + 1) % alBuffers.size
                }
                processedCount--
            }
        }

        return !alSource.isStopped
    }

    fun pause() {
        alSource.pause()
    }

    fun resume() {
        alSource.play()
    }

    fun stop() {
        alSource.stop()

        val al = ALFactory.getAL()
        val queueudCountPtr = intArrayOf(0)
        al.alGetSourcei(alSource.id, AL.AL_BUFFERS_QUEUED, queueudCountPtr, 0)
        val queuedCount = queueudCountPtr[0]
        if (queuedCount > 0) {
            al.alSourceUnqueueBuffers(alSource.id, queuedCount, queueudCountPtr, 0)
        }
        currBuffer = -1
    }

    private fun initializeBuffers(): Boolean {
        for (i in 0 until alBuffers.size) {
            val packet = dataProvider() ?: return false
            alBuffers[i].setDataAndEnqueue(packet)
        }
        return true
    }

    private fun AlBuffer.setDataAndEnqueue(packet: Packet) {
        setData(packet.data, packet.size, format, freq)
        enqueueOnto(alSource)
    }

}


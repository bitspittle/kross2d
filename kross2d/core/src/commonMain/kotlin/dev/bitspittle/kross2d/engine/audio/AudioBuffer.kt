package dev.bitspittle.kross2d.engine.audio

import dev.bitspittle.kross2d.core.event.Event
import dev.bitspittle.kross2d.core.memory.Disposable

/**
 * A class which represents the underlying audio data and logic to play it.
 */
internal expect sealed class AudioBuffer : Disposable {
    /**
     * A streaming buffer, ideal for music.
     *
     * Represents a longer audio clip, one that plays for a long time in the background. As we expect the clip to be
     * very long, potentially, we stream the data regularly instead of loading the data all into memory.
     */
    class Streaming : AudioBuffer {
        fun play()
        fun pause()
        fun reset()
    }

    /**
     * An in-memory buffer, ideal for short sound effects, which can play in parallel with each other.
     *
     * Once in memory, a request to play the audio buffer will result in it pinching off an instance for you.
     */
    class InMemory : AudioBuffer {
        fun play(): Instance

        /**
         * A single instance of the in-memory buffer.
         *
         * Unless looping, it is intended to be used as a one-off and will auto-dispose upon completion.
         */
        class Instance(buffer: InMemory) : Disposable {
            val completed: Event<Unit>

            fun pause()
            fun resume()
        }
    }
}


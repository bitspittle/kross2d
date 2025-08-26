package dev.bitspittle.kross2d.engine.audio

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
        /**
         * Play this music buffer.
         *
         * If already running, will restart.
         */
        fun play(loopCount: Int = Audio.LOOP_CONTINUOUSLY)

        /** Pause this music buffer, if running. */
        fun pause()

        /** Resume this music buffer, if paused. */
        fun resume()
    }

    /**
     * A value between 0 (off) and 1 (full volume).
     *
     * Any number outside of these bounds will be clamped.
     */
    var volume: Float

    /**
     * An in-memory buffer, ideal for short sound effects, which can play in parallel with each other.
     *
     * Once in memory, a request to play the audio buffer will result in it pinching off an instance for you.
     */
    class InMemory : AudioBuffer {
        /**
         * Play this sound effect.
         *
         * This will spawn an [Instance] handle which can be used for controlling the instanced clip.
         *
         * Calling play multiple times in a row will play the sounds in parallel.
         */
        fun play(loopCount: Int = 0): Instance

        /**
         * A single instance of the in-memory buffer.
         *
         * Unless looping, it is intended to be used as a one-off and will auto-dispose upon completion. You can also
         * dispose it directly if you want to stop it early.
         */
        class Instance(buffer: InMemory, loopCount: Int) : Disposable {
            /**
             * A value between 0 (off) and 1 (full volume).
             *
             * Any number outside of these bounds will be clamped.
             */
            var volume: Float

            /** Pause this sound clip. */
            fun pause()

            /** Resume a paused sound clip. */
            fun resume()
        }
    }
}


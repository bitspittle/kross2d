package dev.bitspittle.kross2d.engine.audio

interface Audio {
    companion object {
        const val LOOP_CONTINUOUSLY = Int.MAX_VALUE

        val DefaultPauseGroup = PauseGroup()
    }
}

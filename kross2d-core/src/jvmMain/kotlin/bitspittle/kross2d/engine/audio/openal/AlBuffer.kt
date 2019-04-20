package bitspittle.kross2d.engine.audio.openal

import bitspittle.kross2d.core.memory.Disposable

interface AlBuffer: Disposable {
    val id: Int
}
package dev.bitspittle.kross2d.engine.context

import dev.bitspittle.kross2d.engine.GameState
import dev.bitspittle.kross2d.engine.graphics.DrawSurface
import dev.bitspittle.kross2d.engine.graphics.Screen
import dev.bitspittle.kross2d.engine.time.Timer

/**
 * Classes that may be useful to [GameState.update]
 */
interface DrawContext {
    val screen: Screen
    val timer: Timer
}
package bitspittle.kross2d.engine.context

import bitspittle.kross2d.engine.GameState
import bitspittle.kross2d.engine.assets.Assets
import bitspittle.kross2d.engine.graphics.DrawSurface
import bitspittle.kross2d.engine.time.Timer

/**
 * Classes that may be useful to [GameState.update]
 */
interface DrawContext {
    val assets: Assets
    val screen: DrawSurface
    val timer: Timer
}
package bitspittle.kross2d.engine.context

import bitspittle.kross2d.engine.GameState
import bitspittle.kross2d.engine.assets.AssetLoader
import bitspittle.kross2d.engine.graphics.ImmutableDrawSurface
import bitspittle.kross2d.engine.graphics.ImmutableScreen
import bitspittle.kross2d.engine.memory.Scopes
import bitspittle.kross2d.engine.time.Timer

/**
 * Classes that may be useful to [GameState.enter]
 */
interface EnterContext {
    val assetLoader: AssetLoader
    val screen: ImmutableScreen
    val timer: Timer
    val scopes: Scopes
}
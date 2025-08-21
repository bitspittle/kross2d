package dev.bitspittle.kross2d.engine.context

import dev.bitspittle.kross2d.engine.GameState
import dev.bitspittle.kross2d.engine.assets.AssetLoader
import dev.bitspittle.kross2d.engine.graphics.ImmutableDrawSurface
import dev.bitspittle.kross2d.engine.graphics.ImmutableScreen
import dev.bitspittle.kross2d.engine.memory.Scopes
import dev.bitspittle.kross2d.engine.time.Timer

/**
 * Classes that may be useful to [GameState.enter]
 */
interface EnterContext {
    val assetLoader: AssetLoader
    val screen: ImmutableScreen
    val timer: Timer
    val scopes: Scopes
}
package bitspittle.kross2d.engine.context

import bitspittle.kross2d.engine.GameState
import bitspittle.kross2d.engine.app.ApplicationFacade
import bitspittle.kross2d.engine.assets.AssetLoader
import bitspittle.kross2d.engine.graphics.ImmutableDrawSurface
import bitspittle.kross2d.engine.graphics.ImmutableScreen
import bitspittle.kross2d.engine.input.Keyboard
import bitspittle.kross2d.engine.input.Mouse
import bitspittle.kross2d.engine.memory.Scopes
import bitspittle.kross2d.engine.time.Timer

/**
 * Classes that may be useful to [GameState.update]
 */
interface UpdateContext {
    val app: ApplicationFacade
    val assetLoader: AssetLoader
    val screen: ImmutableScreen
    val keyboard: Keyboard
    val mouse: Mouse
    val timer: Timer
    val scopes: Scopes
}
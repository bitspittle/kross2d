package bitspittle.kross2d.engine.context

import bitspittle.kross2d.engine.GameState
import bitspittle.kross2d.engine.app.ApplicationFacade
import bitspittle.kross2d.engine.assets.AssetLoader
import bitspittle.kross2d.engine.graphics.ImmutableDrawSurface
import bitspittle.kross2d.engine.time.Timer

/**
 * Classes that may be useful to [GameState.init]
 */
interface InitContext {
    val app: ApplicationFacade
    val assetLoader: AssetLoader
    val screen: ImmutableDrawSurface
    val timer: Timer
}
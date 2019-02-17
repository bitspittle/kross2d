package bitspittle.kross2d.engine.context

import bitspittle.kross2d.engine.GameState
import bitspittle.kross2d.engine.app.ApplicationFacade
import bitspittle.kross2d.engine.graphics.ImmutableDrawSurface
import bitspittle.kross2d.engine.input.Keyboard
import bitspittle.kross2d.engine.time.Timer

/**
 * Classes that may be useful to [GameState.update]
 */
interface UpdateContext {
    val app: ApplicationFacade
    val screen: ImmutableDrawSurface
    val keyboard: Keyboard
    val timer: Timer
}
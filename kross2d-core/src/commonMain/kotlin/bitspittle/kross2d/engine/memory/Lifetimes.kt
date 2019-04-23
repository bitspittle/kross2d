package bitspittle.kross2d.engine.memory

import bitspittle.kross2d.core.memory.ImmutableDisposable
import bitspittle.kross2d.core.memory.Disposable
import bitspittle.kross2d.engine.app.ApplicationFacade
import bitspittle.kross2d.engine.assets.Asset
import bitspittle.kross2d.engine.assets.AssetLoader


/**
 * A collection of useful lifetimes to scope [Disposable]s to.
 *
 * If your own game has its own unique lifetimes, you are encourages to use extension methods so
 * they show up in here.
 */
interface Lifetimes {
    /**
     * A lifetime that doesn't end until just before the app quits
     */
    val app: ImmutableDisposable

    /**
     * A lifetime that ends as soon as the current state exits (e.g. into a new state or back into
     * an old state).
     *
     * This is the default lifetime that [AssetLoader] sets its [Asset]s to.
     *
     * See also: [ApplicationFacade.pushState], [ApplicationFacade.popState],
     * [ApplicationFacade.changeState]
     */
    val currState: ImmutableDisposable
}
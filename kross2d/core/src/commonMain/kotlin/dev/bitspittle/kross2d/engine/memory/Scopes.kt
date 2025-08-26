package dev.bitspittle.kross2d.engine.memory

import dev.bitspittle.kross2d.core.memory.Disposable
import dev.bitspittle.kross2d.engine.app.ApplicationFacade
import dev.bitspittle.kross2d.engine.assets.Asset
import dev.bitspittle.kross2d.engine.assets.AssetLoader


/**
 * A collection of useful scopes to register [Disposable]s against.
 *
 * If your own game has its own unique scopes, you are encouraged to use extension properties so
 * they show up in here.
 */
interface Scopes {
    /**
     * A scope that is active until just before the app quits
     */
    val app: Disposable

    /**
     * A scope that is active while the current state is running (e.g. until it transitions into a
     * new state or back into an old state).
     *
     * This is the default scope that [AssetLoader] sets its [Asset]s to.
     *
     * See also: [ApplicationFacade.pushState], [ApplicationFacade.popState],
     * [ApplicationFacade.changeState]
     */
    val currState: Disposable
}
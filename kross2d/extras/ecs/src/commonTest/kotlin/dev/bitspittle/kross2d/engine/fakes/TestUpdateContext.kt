package dev.bitspittle.kross2d.engine.fakes

import dev.bitspittle.kross2d.engine.app.ApplicationFacade
import dev.bitspittle.kross2d.engine.assets.AssetLoader
import dev.bitspittle.kross2d.engine.context.UpdateContext
import dev.bitspittle.kross2d.engine.graphics.Screen
import dev.bitspittle.kross2d.engine.input.Keyboard
import dev.bitspittle.kross2d.engine.input.Mouse
import dev.bitspittle.kross2d.engine.memory.Scopes
import dev.bitspittle.kross2d.engine.time.Timer

class TestUpdateContext(testTimer: TestTimer = TestTimer()) : UpdateContext {
    override val timer: Timer = testTimer

    override val app: ApplicationFacade
        get() = TODO("not implemented")
    override val assetLoader: AssetLoader
        get() = TODO("not implemented")
    override val screen: Screen
        get() = TODO("not implemented")
    override val keyboard: Keyboard
        get() = TODO("not implemented")
    override val mouse: Mouse
        get() = TODO("Not yet implemented")
    override val scopes: Scopes
        get() = TODO("not implemented")
}
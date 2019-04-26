package bitspittle.kross2d.engine.fakes

import bitspittle.kross2d.engine.app.ApplicationFacade
import bitspittle.kross2d.engine.assets.AssetLoader
import bitspittle.kross2d.engine.context.UpdateContext
import bitspittle.kross2d.engine.fakes.TestTimer
import bitspittle.kross2d.engine.graphics.ImmutableScreen
import bitspittle.kross2d.engine.input.Keyboard
import bitspittle.kross2d.engine.memory.Scopes
import bitspittle.kross2d.engine.time.Timer

class TestUpdateContext(testTimer: TestTimer = TestTimer()) : UpdateContext {
    override val timer: Timer = testTimer

    override val app: ApplicationFacade
        get() = TODO("not implemented")
    override val assetLoader: AssetLoader
        get() = TODO("not implemented")
    override val screen: ImmutableScreen
        get() = TODO("not implemented")
    override val keyboard: Keyboard
        get() = TODO("not implemented")
    override val scopes: Scopes
        get() = TODO("not implemented")
}
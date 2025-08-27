package dev.bitspittle.kross2d.engine.fakes

import dev.bitspittle.kross2d.engine.context.DrawContext
import dev.bitspittle.kross2d.engine.graphics.MutableScreen
import dev.bitspittle.kross2d.engine.time.Timer

class TestDrawContext(testScreen: TestScreen) : DrawContext {
    override val timer: Timer = TestTimer()
    override val screen: MutableScreen = testScreen
}
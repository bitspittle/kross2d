package bitspittle.kross2d.engine.fakes

import bitspittle.kross2d.engine.context.DrawContext
import bitspittle.kross2d.engine.graphics.Screen
import bitspittle.kross2d.engine.time.Timer

class TestDrawContext(testScreen: TestScreen) : DrawContext {
    override val timer: Timer = TestTimer()
    override val screen: Screen = testScreen
}
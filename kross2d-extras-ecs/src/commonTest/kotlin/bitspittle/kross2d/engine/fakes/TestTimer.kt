package bitspittle.kross2d.engine.fakes

import bitspittle.kross2d.core.time.Duration
import bitspittle.kross2d.engine.time.Timer

class TestTimer : Timer {
    override var lastFrame: Duration = Duration.zero()
}
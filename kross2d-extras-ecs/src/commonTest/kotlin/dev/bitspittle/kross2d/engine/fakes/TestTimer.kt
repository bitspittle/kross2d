package dev.bitspittle.kross2d.engine.fakes

import dev.bitspittle.kross2d.core.time.Duration
import dev.bitspittle.kross2d.engine.time.Timer

class TestTimer : Timer {
    override var lastFrame: Duration = Duration.zero()
}
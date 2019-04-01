import bitspittle.kross2d.core.math.Vec2
import bitspittle.kross2d.engine.app.AppParams
import bitspittle.kross2d.engine.app.launch

fun main() {
    val screenSize = Vec2(640, 480)
    launch(
        AppParams("Sounds", screenSize),
        initialState = SoundsState())
}

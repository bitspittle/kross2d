import dev.bitspittle.kross2d.core.math.Vec2
import dev.bitspittle.kross2d.engine.app.AppParams
import dev.bitspittle.kross2d.engine.app.launch

fun main() {
    val screenSize = Vec2(640, 480)
    launch(
        AppParams("Many States", screenSize),
        initialState = InitialState())
}

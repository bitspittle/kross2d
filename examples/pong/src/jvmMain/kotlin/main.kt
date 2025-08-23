import dev.bitspittle.kross2d.engine.app.AppParams
import dev.bitspittle.kross2d.engine.app.launch

fun main() {
    val screenSize = PongState.ArenaSize * 5f
    launch(
        AppParams("Pong", screenSize),
        initialState = PongState())
}

import dev.bitspittle.kross2d.engine.app.AppParams
import dev.bitspittle.kross2d.engine.app.launch
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document

fun main() {
    val canvas = document.querySelector("#glCanvas") as HTMLCanvasElement
    launch(
        AppParams(canvas),
        initialState = YourGameState())
}

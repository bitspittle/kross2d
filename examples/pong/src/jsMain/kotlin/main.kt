import dev.bitspittle.kross2d.engine.app.AppParams
import dev.bitspittle.kross2d.engine.app.launch
import org.w3c.dom.HTMLCanvasElement
import kotlinx.browser.document

fun main() {
    val canvas = document.querySelector("#glCanvas") as HTMLCanvasElement
    launch(
        AppParams(canvas),
        initialState = PongState())
}

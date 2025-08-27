import dev.bitspittle.kross2d.engine.app.AppParams
import dev.bitspittle.kross2d.engine.app.launch
import kotlinx.browser.document
import org.w3c.dom.HTMLCanvasElement

fun main() {
    val canvas = document.querySelector("#glCanvas") as HTMLCanvasElement
    launch(
        AppParams(canvas),
        initialState = InitialState())
}

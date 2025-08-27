import dev.bitspittle.kross2d.engine.app.AppParams
import dev.bitspittle.kross2d.engine.app.launch
import kotlinx.browser.document
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLCanvasElement

fun main() {
    // Delay playing any demo with sounds, because on web, it is an error to play sounds before the
    // user interacts with the site first.
    val playButton = document.querySelector("#playButton") as HTMLButtonElement
    val canvas = document.querySelector("#glCanvas") as HTMLCanvasElement

    playButton.onclick = {
        playButton.hidden = true
        canvas.hidden = false

        launch(
            AppParams(canvas),
            initialState = PongState())
    }
}

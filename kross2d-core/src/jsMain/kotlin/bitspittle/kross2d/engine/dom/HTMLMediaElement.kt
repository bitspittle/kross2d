package bitspittle.kross2d.engine.dom

import org.w3c.dom.HTMLMediaElement

fun HTMLMediaElement.clearSource() {
    removeAttribute("src")
}
import kotlinx.browser.document
import react.create
import react.dom.render

fun main() {
    println("Hello!")
    val root = document.getElementById("root") ?: return
    println(root)
    render(CoverPhotos.create(), root)
}

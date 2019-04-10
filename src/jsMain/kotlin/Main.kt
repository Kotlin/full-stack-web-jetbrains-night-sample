import kotlin.browser.document

fun main() {
    val message = "Kotlin full stack application demo"
    document.getElementById("js-response")?.textContent = message
}
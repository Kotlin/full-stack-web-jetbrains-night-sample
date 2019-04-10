import contrib.enzyme.*
import kotlinx.coroutines.*
import react.RBuilder
import react.buildElements
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.appendText
import kotlin.test.assertEquals

val ReactDOM = kotlinext.js.require("react-dom")

open class EnzymeTest {
    init {
        window.scroll(0.0, 0.0)

        if (document.getElementById("reset-styles") == null) {
            val style = document.createElement("style")
            style.id = "reset-styles"
            style.asDynamic().type = "text/css"
            style.appendText("""
                * {
                    box-sizing: border-box;
                }

                html,
                body {
                    margin: 0;
                    padding: 0;
                }
            """.trimIndent())

            document.head!!.appendChild(style)
        }

        configure(EnzymeOptions(AdapterReact16()))
    }

    fun enzymeMount(handler: RBuilder.() -> Unit): ReactWrapper {
        val appElement = document.getElementById("app") ?: run {
            val element = document.createElement("div")
            element.id = "app"
            document.body!!.appendChild(element)
            element
        }

        if (appElement.childElementCount > 0) {
            ReactDOM.unmountComponentAtNode(appElement)
            assertEquals(0, appElement.childElementCount)
        }

        return enzymeMount(MountOptions(attachTo = appElement), handler)
    }

    fun enzymeMount(options: MountOptions, handler: RBuilder.() -> Unit): ReactWrapper = mount(
        buildElements {
            handler()
        },
        options
    )
}

fun runAsyncTest(block: suspend CoroutineScope.() -> Unit) = GlobalScope.async(
    Dispatchers.Unconfined,
    block = block
).asPromise()

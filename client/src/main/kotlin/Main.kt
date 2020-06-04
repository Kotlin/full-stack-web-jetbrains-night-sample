import contrib.ringui.header.ringHeader
import contrib.ringui.header.ringLogo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.css.paddingLeft
import kotlinx.css.px
import react.buildElement
import react.buildElements
import react.dom.render
import styled.css
import styled.styledA
import view.ApplicationComponent
import view.ErrorBoundaryComponent
import view.jetbrainsLogo
import kotlin.browser.document
import kotlin.coroutines.CoroutineContext

private class Application : CoroutineScope {
    override val coroutineContext: CoroutineContext = Job()

    fun start() {
        document.getElementById("react-app")?.let {
            render(buildElements {
                ringHeader {
                    styledA("/") {
                        css {
                            specific {
                                paddingLeft = 48.px
                            }
                        }
                        ringLogo {
                            attrs {
                                className = "logo"
                                glyph = jetbrainsLogo
                            }
                        }
                    }
                }
                child(ErrorBoundaryComponent::class) {
                    child(ApplicationComponent::class) {
                        attrs.coroutineScope = this@Application
                    }
                }
            }, it)
        }
    }
}

fun main() {
    GlobalStyles.inject()

    Application().start()
}
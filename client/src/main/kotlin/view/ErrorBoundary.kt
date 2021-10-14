package view

import contrib.ringui.ringCode
import contrib.ringui.ringErrorMessage
import contrib.ringui.ringLink
import kotlinext.js.jsObject
import kotlinext.js.require
import kotlinx.css.marginTop
import kotlinx.css.px
import kotlinx.serialization.json.Json
import react.*
import rpc.TransportException
import styled.css
import styled.styledDiv

external interface ErrorBoundaryState : State {
    var error: Throwable?
}

fun ErrorBoundaryState() = jsObject<ErrorBoundaryState> {
    error = null
}

class ErrorBoundaryComponent : RComponent<RProps, ErrorBoundaryState>() {
    init {
        state = ErrorBoundaryState()
    }

    override fun componentDidCatch(error: Throwable, info: ErrorInfo) {
        setState {
            this.error = error
        }
    }

    override fun RBuilder.render() {
        val error = state.error
        if (error != null) {
            styledDiv {
                css {
                    marginTop = 100.px
                }

                ringErrorMessage {
                    attrs {
                        icon = require("@jetbrains/icons/frown.js")
                        if (error is TransportException) {
                            code = "Remote server error"
                            message = "no answer."
                            description =
                                "It is likely temporary problem which is not related with demo. Try again later."
                        } else {
                            code = "Internal error"
                            message = "unexpected."
                            description =
                                "This problem is likely related with demo."

                            ringLink {
                                attrs {
                                    href = "https://github.com/Kotlin/kotlin-full-stack-application-demo"
                                }
                                +"Submit issue"
                            }
                        }
                    }
                    error.message?.let {
                        ringCode {
                            attrs {
                                this.code = it
                            }
                        }
                    }
                }
            }
            return
        }

        props.children()
    }
}
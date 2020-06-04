package view

import contrib.ringui.ringCode
import contrib.ringui.ringErrorMessage
import contrib.ringui.ringLink
import kotlinext.js.require
import kotlinx.css.marginTop
import kotlinx.css.px
import kotlinx.serialization.json.Json
import react.*
import rpc.TransportException
import styled.css
import styled.styledDiv

@JsExport
class ErrorBoundaryState : RState {
    var error: Throwable? = null
}

@JsExport
class ErrorBoundaryComponent : RComponent<RProps, ErrorBoundaryState>() {
    init {
        state = ErrorBoundaryState()
    }

    override fun componentDidCatch(error: Throwable, info: RErrorInfo) {
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
                        icon = require("@jetbrains/icons/frown.svg")
                        if (error is TransportException) {
                            code = "Remote server error"
                            message = "no answer."
                            description =
                                "It is temporary problem which is not related with demo. Try again later."
                        } else {
                            code = "Demo error"
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
                        val jsonElement = Json.parseJson(it)
                        val messageKey = "message"
                        val code = if (jsonElement.contains(messageKey)) {
                            jsonElement.jsonObject.getPrimitive(messageKey).content
                        } else {
                            it
                        }
                        ringCode {
                            attrs {
                                this.code = code
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
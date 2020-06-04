package view

import contrib.ringui.ringCode
import contrib.ringui.ringErrorMessage
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
                            message = "no answer from remote server."
                            description =
                                "This problem is not related with demo. It is no answer from remote server with stub data."
                        } else {
                            code = "Prototype error"
                            description =
                                "This problem is likely related with demo."
                        }
                    }
                    error.message?.let {
                        val jsonElement = Json.parseJson(it)
                        val messageKey = "message"
                        if (jsonElement.contains(messageKey)) {
                            ringCode {
                                attrs {
                                    code = jsonElement.jsonObject.getPrimitive(messageKey).content
                                }
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
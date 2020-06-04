package view

import contrib.ringui.ringAlert
import contrib.ringui.ringCode
import contrib.ringui.ringErrorMessage
import kotlinext.js.require
import kotlinx.serialization.json.Json
import react.*
import rpc.TransportException

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
        console.log("DID CATCH")
        console.log("error", error)
        console.log("info", info)
        setState {
            this.error = error
        }
    }

    override fun RBuilder.render() {
        val error = state.error
        if (error != null) {
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
            return
        }
//        if (state.error != null) {
//            console.log("ERROR RENDER")
//            ringAlert {
//                attrs {
//                    type = "error"
//                }
//                +"Hello"
//            }
//            return
//        }
        console.log("NOT ERROR RENDER")

        props.children()
    }
}
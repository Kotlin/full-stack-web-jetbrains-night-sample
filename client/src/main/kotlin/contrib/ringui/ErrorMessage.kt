package contrib.ringui

import react.Props
import react.RBuilder
import react.RHandler

// https://github.com/JetBrains/ring-ui/blob/master/components/error-message/error-message.js
external interface ErrorMessageProps : Props {
    var icon: dynamic
    var code: String
    var message: String
    var description: String
}

fun RBuilder.ringErrorMessage(handler: RHandler<ErrorMessageProps>) {
    ErrorMessageModule.default {
        handler()
    }
}
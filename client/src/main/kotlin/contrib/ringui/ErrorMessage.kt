package contrib.ringui

import react.RBuilder
import react.RHandler
import react.RProps

// https://github.com/JetBrains/ring-ui/blob/master/components/error-message/error-message.js
external interface ErrorMessageProps : RProps {
    var icon: String
    var code: String
    var message: String
    var description: String
}

fun RBuilder.ringErrorMessage(handler: RHandler<ErrorMessageProps>) {
    RingUI.ErrorMessage {
        handler()
    }
}
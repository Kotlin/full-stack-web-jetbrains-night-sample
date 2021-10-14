package contrib.ringui

import react.PropsWithClassName
import react.RBuilder
import react.RHandler

// https://github.com/JetBrains/ring-ui/blob/master/components/alert/alert.js
external interface AlertProps : PropsWithClassName {
    var timeout: Number
    var onCloseRequest: () -> Unit
    var onClose: () -> Unit
    var isShaking: Boolean
    var isClosing: Boolean
    var inline: Boolean
    var showWithAnimation: Boolean
    var closeable: Boolean
    var type: AlertType
}

typealias AlertType = String

object AlertTypes {
    var ERROR = "error"
    var MESSAGE = "message"
    var SUCCESS = "success"
    var WARNING = "warning"
    var LOADING = "loading"
}

fun RBuilder.ringAlert(handler: RHandler<AlertProps>) {
    AlertModule.default {
        handler()
    }
}
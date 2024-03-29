package contrib.ringui

import react.RBuilder
import react.RHandler
import react.PropsWithClassName

// https://github.com/JetBrains/ring-ui/blob/master/components/dialog/dialog.js
external interface DialogProps : PropsWithClassName {
    var contentClassName: String
    var show: Boolean
    var showCloseButton: Boolean
    var onOverlayClick: () -> Unit
    var onEscPress: () -> Unit
    var onCloseClick: () -> Unit
    // onCloseAttempt is a common callback for ESC pressing and overlay clicking.
    // Use it if you don't need different behaviors for this cases.
    var onCloseAttempt: () -> Unit
    // focusTrap may break popups inside dialog, so use it carefully
    var trapFocus: Boolean
    var autoFocusFirst: Boolean
}

fun RBuilder.ringDialog(show: Boolean, handler: RHandler<DialogProps>) {
    DialogModule.default {
        attrs.show = show
        handler()
    }
}
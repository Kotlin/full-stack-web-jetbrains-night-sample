package contrib.ringui

import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RHandler
import react.dom.WithClassName

// https://github.com/JetBrains/ring-ui/blob/master/components/button/button.js
external interface ButtonProps : WithClassName {
    var theme: String
    var active: Boolean
    var danger: Boolean
    var delayed: Boolean
    var loader: Boolean
    var primary: Boolean

    var short: Boolean
    var text: Boolean
    var inline: Boolean
    var dropdown: Boolean

    var href: String

    var icon: dynamic /* string | func */
    var iconSize: Number
    var iconClassName: String

    var onMouseDown: (MouseEvent) -> Unit
}

fun RBuilder.ringButton(handler: RHandler<ButtonProps>) {
    RingUI.Button {
        handler()
    }
}
package contrib.ringui

import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RHandler
import react.PropsWithClassName

// https://github.com/JetBrains/ring-ui/blob/master/components/link/link.js
external interface LinkProps : PropsWithClassName {
    var inneComponentClassName: String
    var active: Boolean
    var inherit: Boolean
    var pseudo: Boolean
    var hover: Boolean
    var href: String
    var onPlainLeftClick: (MouseEvent) -> Unit
    var onClick: (MouseEvent) -> Unit
}

fun RBuilder.ringLink(handler: RHandler<LinkProps>) {
    LinkModule.default {
        handler()
    }
}
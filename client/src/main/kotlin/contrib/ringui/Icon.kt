package contrib.ringui

import react.RBuilder
import react.RHandler
import react.dom.WithClassName

// https://github.com/JetBrains/ring-ui/blob/master/components/icon/icon.js
external interface IconProps : WithClassName {
    var color: String
    var glyph: dynamic /* string | func */
    var height: Number
    var size: Number
    var width: Number
    var loading: Boolean
}

fun RBuilder.ringIcon(handler: RHandler<IconProps>) {
    RingUI.Icon {
        handler()
    }
}
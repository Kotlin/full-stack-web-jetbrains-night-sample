package contrib.ringui

import react.Props
import react.RBuilder
import react.RHandler

// https://github.com/JetBrains/ring-ui/blob/master/components/code/code.js
external interface CodeProps : Props {
    var code: String
}

fun RBuilder.ringCode(handler: RHandler<CodeProps>) {
    CodeModule.default {
        handler()
    }
}
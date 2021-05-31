package contrib.ringui

import react.RBuilder
import react.RHandler
import react.RProps

// https://github.com/JetBrains/ring-ui/blob/master/components/code/code.js
external interface CodeProps : RProps {
    var code: String
}

fun RBuilder.ringCode(handler: RHandler<CodeProps>) {
    CodeModule.default {
        handler()
    }
}
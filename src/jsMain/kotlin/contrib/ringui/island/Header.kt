package contrib.ringui.island

import react.RElementBuilder
import react.RHandler
import react.dom.WithClassName

// https://github.com/JetBrains/ring-ui/blob/master/components/island/header.js
external interface IslandHeaderProps : WithClassName {
    var border: Boolean
    var wrapWithTitle: Boolean
    var phase: Number
}

fun RElementBuilder<IslandProps>.ringIslandHeader(handler: RHandler<IslandHeaderProps>) {
    IslandModule.Header {
        handler()
    }
}
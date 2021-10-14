package contrib.ringui.island

import react.RElementBuilder
import react.RHandler
import react.PropsWithClassName

// https://github.com/JetBrains/ring-ui/blob/master/components/island/content.js
external interface IslandContentProps : PropsWithClassName {
    var scrollableWrappeComponentClassName: String
    var fade: Boolean
    var bottomBorder: Boolean
    var onScroll: () -> Unit
    var onScrollToBottom: () -> Unit
}

fun RElementBuilder<IslandProps>.ringIslandContent(handler: RHandler<IslandContentProps>) {
    IslandModule.Content {
        handler()
    }
}
package contrib.ringui.island

import react.RBuilder
import react.RClass
import react.RHandler
import react.dom.WithClassName

@JsModule("@jetbrains/ring-ui/components/island/island")
internal external object IslandModule {
    val default: RClass<IslandProps>
    val Content: RClass<IslandContentProps>
    val Header: RClass<IslandHeaderProps>
    val AdaptiveIsland: RClass<IslandProps>
}

// https://github.com/JetBrains/ring-ui/blob/master/components/island/island.js
external interface IslandProps : WithClassName {
    val narrow: Boolean
    val withoutPaddings: Boolean
}

fun RBuilder.ringIsland(handler: RHandler<IslandProps>) {
    IslandModule.default {
        handler()
    }
}

fun RBuilder.ringAdaptiveIsland(handler: RHandler<IslandProps>) {
    IslandModule.AdaptiveIsland {
        handler()
    }
}
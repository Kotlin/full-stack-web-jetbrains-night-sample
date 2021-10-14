package contrib.ringui.island

import react.RBuilder
import react.ComponentClass
import react.RHandler
import react.PropsWithClassName

@JsModule("@jetbrains/ring-ui/components/island/island")
internal external object IslandModule {
    val default: ComponentClass<IslandProps>
    val Content: ComponentClass<IslandContentProps>
    val Header: ComponentClass<IslandHeaderProps>
    val AdaptiveIsland: ComponentClass<IslandProps>
}

// https://github.com/JetBrains/ring-ui/blob/master/components/island/island.js
external interface IslandProps : PropsWithClassName {
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
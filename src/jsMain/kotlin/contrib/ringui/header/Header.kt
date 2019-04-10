package contrib.ringui.header

import contrib.ringui.RingUI
import react.RBuilder
import react.RClass
import react.RHandler
import react.dom.WithClassName

@JsModule("@jetbrains/ring-ui/components/header/header")
internal external object HeaderModule {
    val RerenderableHeader: RClass<HeaderProps>
    val Logo: RClass<HeaderLogoProps>
    val Tray: RClass<HeaderTrayProps>
    val TrayIcon: RClass<WithClassName>
    val Profile: RClass<WithClassName>
    val SmartProfile: RClass<WithClassName>
    val Services: RClass<WithClassName>
    val SmartServices: RClass<WithClassName>
}

// https://github.com/JetBrains/ring-ui/blob/master/components/header/header.js
external interface HeaderProps : WithClassName {
    var spaced: Boolean
    var theme: String
}

fun RBuilder.ringHeader(handler: RHandler<HeaderProps>) {
    RingUI.Header {
        handler()
    }
}

package contrib.ringui.header

import react.RBuilder
import react.ComponentClass
import react.RHandler
import react.PropsWithClassName

@JsModule("@jetbrains/ring-ui/components/header/header")
internal external object HeaderModule {
    val RerenderableHeader: ComponentClass<HeaderProps>
    val Logo: ComponentClass<HeaderLogoProps>
    val Tray: ComponentClass<HeaderTrayProps>
    val TrayIcon: ComponentClass<PropsWithClassName>
    val Profile: ComponentClass<PropsWithClassName>
    val SmartProfile: ComponentClass<PropsWithClassName>
    val Services: ComponentClass<PropsWithClassName>
    val SmartServices: ComponentClass<PropsWithClassName>
}

// https://github.com/JetBrains/ring-ui/blob/master/components/header/header.js
external interface HeaderProps : PropsWithClassName {
    var spaced: Boolean
    var theme: String
}

fun RBuilder.ringHeader(handler: RHandler<HeaderProps>) {
    contrib.ringui.HeaderModule.default {
        handler()
    }
}

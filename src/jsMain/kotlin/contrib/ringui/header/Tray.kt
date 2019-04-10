package contrib.ringui.header

import contrib.ringui.ButtonProps
import react.RElementBuilder
import react.RHandler
import react.dom.WithClassName

// https://github.com/JetBrains/ring-ui/blob/master/components/header/tray.js
external interface HeaderTrayProps : WithClassName

// https://github.com/JetBrains/ring-ui/blob/master/components/header/tray-icon.js
external interface HeaderTrayIconProps : ButtonProps {
    var rotatable: Boolean
}

fun RElementBuilder<HeaderProps>.ringTray(handler: RHandler<HeaderTrayProps>) {
    HeaderModule.Tray {
        handler()
    }
}

fun RElementBuilder<HeaderTrayProps>.ringTrayIcon(handler: RHandler<WithClassName>) {
    HeaderModule.TrayIcon {
        handler()
    }
}
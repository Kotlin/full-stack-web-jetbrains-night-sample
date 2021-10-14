package contrib.ringui

import contrib.ringui.header.HeaderProps
import react.ComponentClass

@JsModule("@jetbrains/ring-ui/components/alert/alert.js")
external object AlertModule {
    val default: ComponentClass<AlertProps>
}

@JsModule("@jetbrains/ring-ui/components/button/button.js")
external object ButtonModule {
    val default: ComponentClass<ButtonProps>
}

@JsModule("@jetbrains/ring-ui/components/code/code.js")
external object CodeModule {
    val default: ComponentClass<CodeProps>
}

@JsModule("@jetbrains/ring-ui/components/dialog/dialog.js")
external object DialogModule {
    val default: ComponentClass<DialogProps>
}

@JsModule("@jetbrains/ring-ui/components/error-message/error-message.js")
external object ErrorMessageModule {
    val default: ComponentClass<ErrorMessageProps>
}

@JsModule("@jetbrains/ring-ui/components/header/header.js")
external object HeaderModule {
    val default: ComponentClass<HeaderProps>
}

@JsModule("@jetbrains/ring-ui/components/link/link.js")
external object LinkModule {
    val default: ComponentClass<LinkProps>
}

@JsModule("@jetbrains/ring-ui/components/icon/icon.js")
external object IconModule {
    val default: ComponentClass<IconProps>
}

@JsModule("@jetbrains/ring-ui/components/user-card/user-card.js")
external object UserCardModule {
    val UserCard: ComponentClass<UserCardProps>
}
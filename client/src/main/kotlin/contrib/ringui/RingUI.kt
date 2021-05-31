package contrib.ringui

import contrib.ringui.header.HeaderProps
import react.RClass

@JsModule("@jetbrains/ring-ui/components/alert/alert.js")
external object AlertModule {
    val default: RClass<AlertProps>
}

@JsModule("@jetbrains/ring-ui/components/button/button.js")
external object ButtonModule {
    val default: RClass<ButtonProps>
}

@JsModule("@jetbrains/ring-ui/components/code/code.js")
external object CodeModule {
    val default: RClass<CodeProps>
}

@JsModule("@jetbrains/ring-ui/components/dialog/dialog.js")
external object DialogModule {
    val default: RClass<DialogProps>
}

@JsModule("@jetbrains/ring-ui/components/error-message/error-message.js")
external object ErrorMessageModule {
    val default: RClass<ErrorMessageProps>
}

@JsModule("@jetbrains/ring-ui/components/header/header.js")
external object HeaderModule {
    val default: RClass<HeaderProps>
}

@JsModule("@jetbrains/ring-ui/components/link/link.js")
external object LinkModule {
    val default: RClass<LinkProps>
}

@JsModule("@jetbrains/ring-ui/components/icon/icon.js")
external object IconModule {
    val default: RClass<IconProps>
}

@JsModule("@jetbrains/ring-ui/components/user-card/user-card.js")
external object UserCardModule {
    val UserCard: RClass<UserCardProps>
}
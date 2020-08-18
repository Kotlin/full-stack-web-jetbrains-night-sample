package contrib.ringui

import contrib.ringui.header.HeaderProps
import react.RClass

@JsModule("@jetbrains/ring-ui")
external object RingUI {
    val Alert: RClass<AlertProps>
    val Button: RClass<ButtonProps>
    val Code: RClass<CodeProps>
    val Dialog: RClass<DialogProps>
    val ErrorMessage: RClass<ErrorMessageProps>
    val Header: RClass<HeaderProps>
    val Link: RClass<LinkProps>
    val Icon: RClass<IconProps>
}

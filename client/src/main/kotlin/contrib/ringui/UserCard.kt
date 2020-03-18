package contrib.ringui

import react.RBuilder
import react.RClass
import react.RHandler
import react.RProps

@JsModule("@jetbrains/ring-ui/components/user-card/user-card")
private external object UserCardModule {
    val UserCard: RClass<UserCardProps>
}

// https://github.com/JetBrains/ring-ui/blob/master/components/user-card/card.js
external interface UserCardProps : RProps {
    var user: UserCardModel
    var wording: UserCardWording
}

external interface UserCardModel{
    val name: String
    val login: String
    val avatarUrl: String
    val email: String? get() = definedExternally
    val href: String? get() = definedExternally
}

data class UserCardModelImpl(
    override val name: String,
    override val login: String,
    override val avatarUrl: String,
    override val email: String? = null,
    override val href: String? = null
) : UserCardModel

data class UserCardWording(
    val banned: String,
    val online: String,
    val offline: String
)

fun RBuilder.ringUserCard(user: UserCardModel, handler: RHandler<UserCardProps> = {}) {
    UserCardModule.UserCard {
        attrs.user = user
        handler()
    }
}
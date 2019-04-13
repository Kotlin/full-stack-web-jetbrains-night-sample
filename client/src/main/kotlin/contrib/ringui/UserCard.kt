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

data class UserCardModel(
    val name: String,
    val login: String,
    val avatarUrl: String,
    val email: String? = null,
    val href: String? = null
)

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
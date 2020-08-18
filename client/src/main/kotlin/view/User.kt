package view

import contrib.ringui.UserCardModel
import contrib.ringui.UserCardModelImpl
import contrib.ringui.ringUserCard
import model.User
import react.RBuilder
import styled.DIVBuilder
import styled.styledDiv

private fun User.toUserCardModel() = UserCardModelImpl(
    name = this.name,
    login = this.username,
    avatarUrl = "https://i.pravatar.cc/56?u=${this.username}",
    email = this.email
)

fun RBuilder.userView(user: User, builder: DIVBuilder = {}) {
    styledDiv {
        ringUserCard(user.toUserCardModel())
        builder()
    }
}
package model

import kotlinx.serialization.Serializable

@Serializable
data class PostWithComments(
    val post: Post,
    val comments: List<Comment>
)

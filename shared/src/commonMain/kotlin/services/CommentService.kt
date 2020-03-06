package services

import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import model.Comment
import network.CommentClient
import kotlin.coroutines.CoroutineContext
import kotlin.math.min

class CommentsService(coroutineContext: CoroutineContext) {
    private val client = CommentClient(coroutineContext)

    suspend fun getComments(postId: String): List<Comment> {
        return Json.parse(
            Comment.serializer().list,
            client.getComments(postId)
        )
    }

    suspend fun getComments(postId: String, count: Int = 5): List<Comment> {
        return when (count) {
            0 -> emptyList()
            else -> Json.parse(
                Comment.serializer().list,
                client.getComments(postId, count)
            ).take(count)
        }
    }
}
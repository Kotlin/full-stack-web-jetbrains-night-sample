package services

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import model.Comment
import network.CommentClient
import kotlin.coroutines.CoroutineContext

class CommentsService(coroutineContext: CoroutineContext) {
    private val client = CommentClient(coroutineContext)

    suspend fun getComments(postId: String): List<Comment> {
        return Json.decodeFromString(
            ListSerializer(Comment.serializer()),
            client.getComments(postId)
        )
    }

    suspend fun getComments(postId: String, count: Int = 5): List<Comment> {
        return when (count) {
            0 -> emptyList()
            else -> Json.decodeFromString(
                ListSerializer(Comment.serializer()),
                client.getComments(postId, count)
            ).take(count)
        }
    }
}
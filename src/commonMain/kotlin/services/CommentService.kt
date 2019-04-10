package services

import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import model.Comment
import network.CommentClient
import kotlin.coroutines.CoroutineContext

class CommentsService(coroutineContext: CoroutineContext) {
    private val client = CommentClient(coroutineContext)

    suspend fun getComments(postId: String): List<Comment> {
        return Json.parse(
            Comment.serializer().list,
            client.getComments(postId)
        )
    }

    suspend fun getComments(postId: String, from: Int = 0, count: Int = 5): List<Comment> {
        return Json.parse(
            Comment.serializer().list,
            client.getComments(postId)
        ).subList(from, from + count)
    }
}
package network

import kotlin.coroutines.CoroutineContext

expect class CommentClient(coroutineContext: CoroutineContext) {
    suspend fun getComments(postId: String): String
}
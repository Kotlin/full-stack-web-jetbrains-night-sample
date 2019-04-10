package network

import JSON_PLACEHOLDER_URL
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

actual class CommentClient actual constructor(coroutineContext: CoroutineContext) {
    private val client = HttpClient(Apache)

    actual suspend fun getComments(postId: String): String {
        return withContext(coroutineContext) {
            client.get<String>("$JSON_PLACEHOLDER_URL/posts/$postId/comments")
        }
    }
}
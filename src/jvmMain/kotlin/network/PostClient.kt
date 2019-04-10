package network

import JSON_PLACEHOLDER_URL
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PostClient(private val coroutineContext: CoroutineContext) {
    private val client = HttpClient(Apache)

    suspend fun getPosts(): String {
        return withContext(coroutineContext) {
            client.get<String>("$JSON_PLACEHOLDER_URL/posts")
        }
    }
}
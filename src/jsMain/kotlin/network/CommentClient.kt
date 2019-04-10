package network

import JSON_PLACEHOLDER_URL
import kotlinx.coroutines.await
import kotlinx.coroutines.withContext
import org.w3c.fetch.RequestCredentials
import org.w3c.fetch.RequestInit
import kotlin.browser.window
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.js.json

actual class CommentClient actual constructor(coroutineContext: CoroutineContext) {
    actual suspend fun getComments(postId: String): String {
        val url = "$JSON_PLACEHOLDER_URL/posts/$postId/comments"

        return withContext(coroutineContext) {
            val response = window.fetch(url, RequestInit("GET", headers = json(
                "Accept" to "application/json",
                "Content-Type" to "application/json"
            ), credentials = "same-origin".asDynamic())).await()

            response.text().await()
        }
    }
}
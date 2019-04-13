package network

import FAKE_JSON_URL
import JSON_PLACEHOLDER_URL
import kotlinx.coroutines.await
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.w3c.fetch.RequestCredentials
import org.w3c.fetch.RequestInit
import org.w3c.fetch.SAME_ORIGIN
import kotlin.browser.window
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.js.json

actual class CommentClient actual constructor(coroutineContext: CoroutineContext) {
    private var fallback = false

    private val headers = json(
        "Accept" to "application/json",
        "Content-Type" to "application/json"
    )

    actual suspend fun getComments(postId: String, count: Int): String {
        return withContext(coroutineContext) {
            val fakeJsonRequestBody = FakeJsonCommentRequest.create(postId.toInt(), count)
            val fakeJsonResponse = if (!fallback) {
                window.fetch(
                    FAKE_JSON_URL,
                    RequestInit(
                        "POST",
                        headers = headers,
                        credentials = RequestCredentials.SAME_ORIGIN,
                        body = Json.stringify(FakeJsonCommentRequest.serializer(), fakeJsonRequestBody)
                    )
                ).await()
            } else {
                null
            }

            if (fakeJsonResponse?.status == 200.toShort()) {
                if (count <= 1) {
                    "[${fakeJsonResponse.text().await()}]"
                }
                else {
                    fakeJsonResponse.text().await()
                }
            } else {
                fallback = true

                val url = "$JSON_PLACEHOLDER_URL/posts/$postId/comments"
                val response = window.fetch(url, RequestInit(
                    "GET",
                    headers = headers,
                    credentials = RequestCredentials.SAME_ORIGIN
                )).await()

                response.text().await()
            }
        }
    }
}
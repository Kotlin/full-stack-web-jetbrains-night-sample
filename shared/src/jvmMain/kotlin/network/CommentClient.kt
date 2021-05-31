package network

import FAKE_JSON_URL
import JSON_PLACEHOLDER_URL
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.*
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.content.TextContent
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

actual class CommentClient actual constructor(coroutineContext: CoroutineContext) {
    private val client = HttpClient(Apache)

    private val json = jacksonObjectMapper()

    private var fallback = false

    actual suspend fun getComments(postId: String, count: Int): String {
        return withContext(coroutineContext) {
            val fakeJsonRequestBody = FakeJsonCommentRequest.create(postId.toInt(), count)
            val fakeJsonResponse = if (!fallback) {
                try {
                    client.request<HttpStatement>(FAKE_JSON_URL) {
                        method = HttpMethod.Post
                        body = TextContent(
                            json.writeValueAsString(fakeJsonRequestBody),
                            contentType = ContentType.Application.Json
                        )
                    }.execute()
                } catch (e: ClientRequestException) {
                    null
                }
            } else {
                null
            }

            if (fakeJsonResponse?.status?.value == 200) {
                if (count <= 1) {
                    "[${fakeJsonResponse.readText()}]"
                } else {
                    fakeJsonResponse.readText()
                }
            } else {
                fallback = true
                client.get("$JSON_PLACEHOLDER_URL/posts/$postId/comments")
            }
        }
    }
}
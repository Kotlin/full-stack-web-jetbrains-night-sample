package network

import FAKE_JSON_TOKEN
import FAKE_JSON_URL
import JSON_PLACEHOLDER_URL
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.content.TextContent
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlin.coroutines.CoroutineContext

@Serializable
private class FakeJsonPostRequest {
    val token = FAKE_JSON_TOKEN
    val data = Data()

    @Serializable
    class Data {
        val userId = "numberInt|1,10"
        val id = "numberInt"
        val title = "stringShort"
        val body = "stringLong"
        val `_repeat` = 10
    }
}

class PostClient(private val coroutineContext: CoroutineContext) {
    private val client = HttpClient(Apache)

    private val json = jacksonObjectMapper()

    private var fallback = false

    suspend fun getPosts(): String {
        return withContext(coroutineContext) {
            val fakeJsonResponse = if (!fallback) {
                client.request<HttpResponse>(FAKE_JSON_URL) {
                    method = HttpMethod.Post
                    body = TextContent(
                        json.writeValueAsString(FakeJsonPostRequest()),
                        contentType = ContentType.Application.Json
                    )
                }
            } else {
                null
            }

            if (fakeJsonResponse?.status?.value == 200) {
                fakeJsonResponse.readText()
            } else {
                fallback = true
                client.get("$JSON_PLACEHOLDER_URL/posts")
            }
        }
    }
}

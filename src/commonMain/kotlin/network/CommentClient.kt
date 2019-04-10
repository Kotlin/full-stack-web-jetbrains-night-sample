package network

import FAKE_JSON_TOKEN
import kotlinx.serialization.Serializable
import kotlin.coroutines.CoroutineContext

@Serializable
internal class FakeJsonCommentRequest {
    companion object {
        fun create(postId: Int, count: Int): FakeJsonCommentRequest {
            return FakeJsonCommentRequest().apply {
                data = data.apply {
                    this.postId = postId
                    this._repeat = count
                }
            }
        }
    }
    val token = FAKE_JSON_TOKEN
    var data = FakeJsonCommentData()
}

@Serializable
internal class FakeJsonCommentData {
    var postId = 0
    val id = "numberInt"
    val name = "name"
    val email = "internetEmail"
    val body = "stringShort"
    var _repeat = 1
}


expect class CommentClient(coroutineContext: CoroutineContext) {
    suspend fun getComments(postId: String, count: Int = 5): String
}
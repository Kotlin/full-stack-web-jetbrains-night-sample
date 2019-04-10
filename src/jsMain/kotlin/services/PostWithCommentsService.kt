package services

import model.PostWithComments
import rpc.Transport
import kotlin.coroutines.CoroutineContext

actual class PostWithCommentsService(coroutineContext: CoroutineContext) {
    private val transport = Transport(coroutineContext)

    actual suspend fun getPostWithComments(postId: String): PostWithComments {
        return transport.get("getPostWithComments", PostWithComments.serializer(), "postId" to postId)
    }

    actual suspend fun getPostsWithComments(): List<PostWithComments> {
        return transport.getList("getPostsWithComments", PostWithComments.serializer())
    }
}
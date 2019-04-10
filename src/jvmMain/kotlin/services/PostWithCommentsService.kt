package services

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import model.PostWithComments
import rpc.RPCService
import kotlin.random.Random

actual class PostWithCommentsService : RPCService {
    private val postService = PostService()
    private val commentsService = CommentsService(GlobalScope.coroutineContext)

    actual suspend fun getPostsWithComments(): List<PostWithComments> {
        return postService.getPosts().map { post ->
            GlobalScope.async {
                val comments = commentsService.getComments(post.id.toString(), count = Random.nextInt(1, 3))
                PostWithComments(post, comments)
            }
        }.map { it.await() }
    }

    actual suspend fun getPostWithComments(postId: String): PostWithComments {
        val post = postService.getPost(postId)
        val comments = commentsService.getComments(post.id.toString())
        return PostWithComments(post, comments)
    }
}
package services

import model.Post

expect class PostService {
    suspend fun getPost(id: String): Post

    suspend fun getPosts(): List<Post>
}
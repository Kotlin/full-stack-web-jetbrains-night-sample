import model.Post
import kotlin.test.Test
import kotlin.test.assertEquals

class CommonTests {
    @Test
    fun modelEquality() {
        val postA = Post(userId = 1, id = 1, title = "Post title", body = "Kotlin is fun")
        val postB = Post(userId = 1, id = 1, title = "Post title", body = "Kotlin is fun")
        assertEquals(postA, postB, "Data classes are equal if all fields are equal")
    }
}
import model.Comment
import model.Post
import model.PostWithComments
import view.PostState
import view.PostView
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class SampleTests {
    @Test
    fun testMath() {
        assertEquals(4, 2 * 2)
        assertNotEquals(5, 2 + 2)
    }
}

class SampleEnzymeTest: EnzymeTest() {
    @Test
    fun testPostView() = runAsyncTest {
        var callCount = 0
        val testPostData = PostWithComments(
            post = Post(1, 1, "title", "body"),
            comments = mutableListOf(
                Comment(1, 1, "John.Doe", "john.doe@example.com", "comment"),
                Comment(1, 2, "Jane.Doe", "jane.doe@example.com", "comment")
            )
        )

        val element = enzymeMount {
            child(PostView::class) {
                attrs.postWithComments = testPostData
                attrs.onMoreComments = {
                    callCount++
                }
            }
        }

        assertEquals(2, element.find(".PostStyles-comment").map { it.domInstance() }.size)

        element.find("button").simulate("mousedown")

        assertEquals(1, callCount)
        assertTrue(element.find("PostView").state<PostState>().loading)
    }
}
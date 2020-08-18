package view

import contrib.ringui.header.ringHeader
import contrib.ringui.header.ringLogo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.css.marginBottom
import kotlinx.css.padding
import kotlinx.css.paddingLeft
import kotlinx.css.px
import model.PostWithComments
import model.User
import react.*
import services.CommentsService
import services.PostWithCommentsService
import services.UserService
import styled.StyleSheet
import styled.css
import styled.styledA
import styled.styledDiv
import kotlin.random.Random

private object ApplicationStyles : StyleSheet("ApplicationStyles", isStatic = true) {
    val wrapper by css {
        padding(32.px, 16.px)
    }

    val post by css {
        marginBottom = 32.px
    }
}

external interface ApplicationProps : RProps {
    var coroutineScope: CoroutineScope
}

@JsExport
class ApplicationState : RState {
    var error: Throwable? = null
    var postWithComments: List<PostWithComments> = emptyList()
    var users: List<User> = emptyList()
}

/*
TODO likely it also should be rewritten to something like below, but it doesn't work for some reason.
external interface ApplicationState : RState {
    var postWithComments: List<PostWithComments>
    var users: List<User>
}

fun ApplicationState() = object : ApplicationState {
    override var postWithComments: List<PostWithComments> = emptyList()
    override var users: List<User> = emptyList()
}
*/

@JsExport
class ApplicationComponent : RComponent<ApplicationProps, ApplicationState>() {
    init {
        state = ApplicationState()
    }

    private val coroutineContext
        get() = props.coroutineScope.coroutineContext

    override fun componentDidMount() {
        val postWithCommentsService = PostWithCommentsService(coroutineContext)
        val userService = UserService(coroutineContext)

        props.coroutineScope.launch {
            val posts = try {
                postWithCommentsService.getPostsWithComments()
            } catch (e: Throwable) {
                setState {
                    error = e
                }
                return@launch
            }

            setState {
                postWithComments += posts
            }

            // Parallel coroutines execution example
            val userIds = posts.map { it.post.userId }.toSet()
            val users = try {
                userIds
                    .map { async { userService.getUser(it) } }
                    .map { it.await() }
            } catch (e: Throwable) {
                setState {
                    error = e
                }
                return@launch
            }

            setState {
                this.users = users
            }
        }
    }

    override fun RBuilder.render() {
        val error = state.error
        if (error != null) {
            throw error
        }

        styledDiv {
            css {
                +ApplicationStyles.wrapper
            }

            state.postWithComments.map { postWithComments ->
                styledDiv {
                    css {
                        +ApplicationStyles.post
                    }
                    postView(
                        postWithComments,
                        state.users.find { it.id == postWithComments.post.userId },
                        onMoreComments = {
                            onMoreComment(postWithComments.post.id)
                        })
                }
            }
        }
    }

    private fun onMoreComment(postId: Int) {
        val commentsService = CommentsService(coroutineContext)
        val post = state.postWithComments.find { it.post.id == postId }

        if (post != null) {
            props.coroutineScope.launch {
                val comments = commentsService.getComments(postId.toString(), Random.nextInt(4))

                setState {
                    postWithComments = postWithComments.map {
                        if (it != post) it else PostWithComments(it.post, it.comments + comments)
                    }
                }
            }
        }
    }
}

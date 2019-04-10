package view

import contrib.ringui.header.ringHeader
import contrib.ringui.header.ringLogo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.css.padding
import kotlinx.css.px
import model.Post
import model.User
import react.*
import services.PostService
import services.UserService
import styled.StyleSheet
import styled.css
import styled.styledA
import styled.styledDiv
import kotlin.random.Random

private val jetbrainsLogo = kotlinext.js.require("@jetbrains/logos/jetbrains/jetbrains-simple.svg")

private object ApplicationStyles: StyleSheet("ApplicationStyles", isStatic = true) {
    val wrapper by css {
        padding(32.px, 16.px)
    }

    val post by css {
        marginBottom = 32.px
    }
}

interface ApplicationProps: RProps {
    var coroutineScope: CoroutineScope
}

class ApplicationState: RState {
    var posts: List<Post> = emptyList()
    var users: Map<Int, User> = emptyMap()
}

class ApplicationComponent: RComponent<ApplicationProps, ApplicationState>() {
    init {
        state = ApplicationState()
    }

    private val coroutineContext
        get() = props.coroutineScope.coroutineContext

    override fun componentDidMount() {
        val postService = PostService(coroutineContext)
        val userService = UserService(coroutineContext)

        props.coroutineScope.launch {
            val posts = postService.getPosts()
            setState {
                this.posts += posts
            }

            // Parallel coroutines execution example
            val userIds = posts.map { it.userId }.toSet()
            val users = userIds
                .map { async { userService.getUser(it) } }
                .map { it.await() }
                .toSet()

            setState {
                this.users = users.associateBy { it.id }
            }
        }
    }

    override fun RBuilder.render() {
        ringHeader {
            styledA("/") {
                css {
                    specific {
                        paddingLeft = 48.px
                    }
                }
                ringLogo {
                    attrs {
                        className = "logo"
                        glyph = jetbrainsLogo
                    }
                }
            }
        }

        styledDiv {
            css {
                +ApplicationStyles.wrapper
            }

            state.posts.map { post ->
                styledDiv {
                    css {
                        +ApplicationStyles.post
                    }
                    postView(post, state.users[post.userId])
                }
            }
        }
    }
}
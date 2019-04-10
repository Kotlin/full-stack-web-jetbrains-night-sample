package view

import contrib.ringui.header.ringHeader
import contrib.ringui.header.ringLogo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.css.padding
import kotlinx.css.px
import model.Post
import react.*
import services.PostService
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
}

class ApplicationComponent: RComponent<ApplicationProps, ApplicationState>() {
    init {
        state = ApplicationState()
    }

    private val coroutineContext
        get() = props.coroutineScope.coroutineContext

    override fun componentDidMount() {
        val postService = PostService(coroutineContext)

        props.coroutineScope.launch {
            val posts = postService.getPosts()
            setState {
                this.posts += posts
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
                    postView(post)
                }
            }
        }
    }
}
import database.Posts
import database.database
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.content.*
import io.ktor.jackson.*
import io.ktor.routing.*
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.css.properties.lh
import kotlinx.html.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import model.Post
import model.PostWithComments
import network.PostClient
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.batchInsert
import rpc.rpc
import services.PostService
import services.PostWithCommentsService

private val globalCss = CssBuilder().apply {
    body {
        margin(0.px)
        padding(0.px)

        fontSize = 13.px
        fontFamily =
            "-system-ui, -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Droid Sans, Helvetica Neue, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Droid Sans, Helvetica Neue, Arial, sans-serif"

        lineHeight = 20.px.lh
    }
}

fun Application.main() {
    install(ContentNegotiation) {
        jackson {}
    }

    database {
        SchemaUtils.create(Posts)
    }

    launch {
        val result = PostClient(this.coroutineContext).getPosts()
        val posts = Json.decodeFromString(ListSerializer(Post.serializer()), result)

        database {
            Posts.batchInsert(posts) {
                this[Posts.postId] = it.id
                this[Posts.userId] = it.userId
                this[Posts.title] = it.title.take(50)
                this[Posts.body] = it.body.take(255)
            }
        }
    }

    routing {
        get("/") {
            call.respondHtml {
                head {
                    meta {
                        charset = "utf-8"
                    }
                    title {
                        +"Kotlin full stack application demo"
                    }
                    style {
                        unsafe {
                            +globalCss.toString()
                        }
                    }
                }
                body {
                    div {
                        id = "react-app"
                        +"Loading..."
                    }
                    script(src = "/client.js") {
                    }
                }
            }
        }

        static("/") {
            resources("/")
        }

        route("/api") {
            rpc(PostService::class, Post.serializer())
            rpc(PostWithCommentsService::class, PostWithComments.serializer())
        }
    }
}
import database.Posts
import database.database
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.content.files
import io.ktor.http.content.static
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.css.properties.lh
import kotlinx.html.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import model.Post
import network.PostClient
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.batchInsert

private val globalCss = CSSBuilder().apply {
    body {
        margin(0.px)
        padding(0.px)

        fontSize = 13.px
        fontFamily = "-system-ui, -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Droid Sans, Helvetica Neue, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Droid Sans, Helvetica Neue, Arial, sans-serif"

        lineHeight = 20.px.lh
    }
}

fun Application.main() {
    database {
        SchemaUtils.create(Posts)
    }

    launch {
        val result = PostClient(this.coroutineContext).getPosts()
        val posts = Json.parse(Post.serializer().list, result)

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
                    script(src = "/main.bundle.js") {
                    }
                }
            }
        }

        static("/") {
            files("build/bundle")
        }
    }
}
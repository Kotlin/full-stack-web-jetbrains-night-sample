import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.content.files
import io.ktor.http.content.static
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.css.*
import kotlinx.css.properties.lh
import kotlinx.html.*
import java.io.File

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
    val currentDir = File(".").absoluteFile
    environment.log.info("Current directory: $currentDir")

    val webDir = listOf(
        "web",
        "../src/jsMain/web",
        "src/jsMain/web"
    ).map {
        File(currentDir, it)
    }.firstOrNull { it.isDirectory }?.absoluteFile ?: error("Can't find 'web' folder for this sample")

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
                        id = "js-response"
                        +"Loading..."
                    }
                    script(src = "/static/require.min.js") {
                    }
                    script {
                        +"require.config({baseUrl: '/static'});\n"
                        +"require(['/static/kotlin-full-stack-application-demo.js']);"
                    }
                }
            }
        }

        static("/static") {
            files(webDir)
        }
    }
}
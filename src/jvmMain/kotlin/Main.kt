import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.content.files
import io.ktor.http.content.static
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.html.*
import java.io.File

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
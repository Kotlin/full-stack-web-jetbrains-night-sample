rootProject.name = "kotlin-full-stack-application-demo"

pluginManagement {
    resolutionStrategy {
        repositories {
            gradlePluginPortal()
            maven("https://dl.bintray.com/kotlin/kotlin-eap")
            maven("https://dl.bintray.com/kotlin/kotlin-dev")
        }
    }
}

include("shared")
include("client")
include("server")
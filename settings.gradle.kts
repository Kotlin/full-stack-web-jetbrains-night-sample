rootProject.name = "kotlin-full-stack-application-demo"

pluginManagement {
    resolutionStrategy {
        repositories {
            gradlePluginPortal()
        }
    }
}

include("shared")
include("client")
include("server")
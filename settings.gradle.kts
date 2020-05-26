rootProject.name = "kotlin-full-stack-application-demo"

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "kotlinx-serialization") {
                useModule("org.jetbrains.kotlin:kotlin-serialization:${requested.version}")
            }
        }
    }

    repositories {
        mavenCentral()
        
        maven(url = "https://dl.bintray.com/kotlin/kotlin-dev")
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
        
        maven(url = "https://plugins.gradle.org/m2/")
    }
}

include("shared")
include("client")
include("server")

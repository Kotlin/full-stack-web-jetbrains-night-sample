plugins {
    kotlin("multiplatform") version "1.4.21" apply false
    kotlin("plugin.serialization") version "1.4.21" apply false
}

allprojects {
    version = "0.1.1"

    repositories {
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlinx")
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://dl.bintray.com/kotlin/kotlin-dev")
    }
}

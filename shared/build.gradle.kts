import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("kotlinx-serialization")
}

repositories {
    maven(url = "https://dl.bintray.com/kotlin/kotlin-dev")
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/ktor")
    maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    maven(url = "https://dl.bintray.com/kotlin/kotlinx")
    maven(url = "https://dl.bintray.com/kotlin/exposed")
    mavenCentral()
}

val kotlinVersion = "pre.94-kotlin-1.3.70" // for kotlin-wrappers
val ktorVersion = "1.3.2"
val kotlinxSerializationVersion = "0.20.0"
val logbackVersion = "1.2.3"

kotlin {
    jvm()
    js {
        browser {
            testTask {
                testLogging {
                    showExceptions = true
                    exceptionFormat = FULL
                    showCauses = true
                    showStackTraces = true
                }
            }
        }
        nodejs {
            testTask {
                testLogging {
                    showExceptions = true
                    exceptionFormat = FULL
                    showCauses = true
                    showStackTraces = true
                }
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains:kotlin-css:1.0.0-$kotlinVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$kotlinxSerializationVersion")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
                implementation("io.ktor:ktor-client-apache:$ktorVersion")
                implementation("io.ktor:ktor-jackson:$ktorVersion")
                implementation("io.ktor:ktor-html-builder:$ktorVersion")
                implementation("ch.qos.logback:logback-classic:$logbackVersion")
                implementation("org.jetbrains:kotlin-css-jvm:1.0.0-$kotlinVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$kotlinxSerializationVersion")
                implementation("org.jetbrains.exposed:exposed-core:0.22.1")
                implementation("org.jetbrains.exposed:exposed-dao:0.22.1")
                implementation("org.jetbrains.exposed:exposed-jdbc:0.22.1")
                implementation("com.h2database:h2:1.4.200")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$kotlinxSerializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.3")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}
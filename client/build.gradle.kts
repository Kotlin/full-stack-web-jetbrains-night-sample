plugins {
    kotlin("js")
}

val kotlinxHtmlVersion = project.property("kotlinx.html.version") as String
val kotlinxSerializationVersion = project.property("kotlinx.serialization.version") as String
val kotlinxCoroutinesVersion = project.property("kotlinx.coroutines.version") as String
val kotlinWrappersSuffix = project.property("kotlin.wrappers.suffix") as String

kotlin {
    js {
        useCommonJs()
        browser {
        }
        binaries.executable()
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-js")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-js:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$kotlinxCoroutinesVersion")

    implementation(project(":shared"))

    implementation(npm("core-js", "2.6.5"))
    implementation(npm("svg-inline-loader", "0.8.2"))
    implementation("org.jetbrains.kotlinx:kotlinx-html:$kotlinxHtmlVersion")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-$kotlinWrappersSuffix")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-$kotlinWrappersSuffix")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:5.3.0-$kotlinWrappersSuffix")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1-$kotlinWrappersSuffix")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-$kotlinWrappersSuffix")
    implementation(npm("inline-style-prefixer", "6.0.0"))
    implementation(npm("@jetbrains/logos", "1.4.16"))
    implementation(npm("@jetbrains/ring-ui", "4.0.12"))
    implementation(npm("url-loader", "4.1.1"))

    testImplementation("org.jetbrains.kotlin:kotlin-test-js")
    testImplementation(npm("enzyme", "3.11.0"))
    testImplementation(npm("enzyme-adapter-react-16", "1.12.1"))
}

tasks.named("run") {
    dependsOn(":server:prepareDevServer")
}

val browserDist by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}

artifacts {
    add(browserDist.name, tasks.named("browserDistribution").map { it.outputs.files.files.single() })
}
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

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$kotlinxCoroutinesVersion")

    implementation(project(":shared"))

    implementation(npm("core-js", "2.6.5"))
    implementation(npm("svg-inline-loader", "0.8.0"))
    implementation("org.jetbrains.kotlinx:kotlinx-html:$kotlinxHtmlVersion")
    implementation("org.jetbrains:kotlin-react:16.13.1-$kotlinWrappersSuffix")
    implementation(npm("react", "16.13.1"))
    implementation(npm("react-dom", "16.13.1"))
    implementation(npm("react-is", "16.13.1"))
    implementation("org.jetbrains:kotlin-react-dom:16.13.1-$kotlinWrappersSuffix")
    implementation("org.jetbrains:kotlin-styled:1.0.0-$kotlinWrappersSuffix")
    implementation("org.jetbrains:kotlin-extensions:1.0.1-$kotlinWrappersSuffix")
    implementation("org.jetbrains:kotlin-css:1.0.0-$kotlinWrappersSuffix")
    implementation(npm("inline-style-prefixer", "5.1.0"))
    implementation(npm("styled-components", "4.3.2"))
    implementation(npm("@jetbrains/logos", "1.1.6"))
    implementation(npm("@jetbrains/ring-ui", "3.0.4"))

    testImplementation("org.jetbrains.kotlin:kotlin-test-js")
    testImplementation(npm("enzyme", "3.9.0"))
    testImplementation(npm("enzyme-adapter-react-16", "1.12.1"))
}

tasks.named("run") {
    dependsOn(":server:prepareDevServer")
}
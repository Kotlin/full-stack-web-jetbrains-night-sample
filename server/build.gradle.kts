import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

plugins {
    id("java")
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("application")
    id("distribution")
}

val kotlinxSerializationVersion = project.property("kotlinx.serialization.version") as String
val ktorVersion = project.property("ktor.version") as String
val kotlinWrappersSuffix = project.property("kotlin.wrappers.suffix") as String

val logbackVersion = project.property("logback.version") as String
val exposedVersion = project.property("exposed.version") as String
val h2Version = project.property("h2.version") as String

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-client-apache:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.jetbrains:kotlin-css:1.0.0-$kotlinWrappersSuffix")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("com.h2database:h2:$h2Version")

    implementation(project(":shared"))

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

tasks.withType<Copy>().named("processResources") {
    from(project(":client").tasks.named("browserDistribution"))
}


////// Dev server

tasks.register<Copy>("devServerResources") {
    destinationDir = File(project.buildDir, "dev-resources")
    from(sourceSets.main.map { it.resources })
    filter { line -> line.replace("port = 8080", "port = 8081") }
}

tasks.register("prepareDevServer") {
    dependsOn("compileKotlin")
    dependsOn("devServerResources")
}

tasks.register<JavaExec>("devServer") {
    dependsOn("prepareDevServer")

    classpath = project.files(
            configurations.runtimeClasspath,
            File(project.buildDir, "classes/kotlin/main"),
            File(project.buildDir, "dev-resources")
    )
    main = "io.ktor.server.netty.EngineMain"
}

///////////////
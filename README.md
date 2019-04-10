# kotlin-full-stack-application-demo

A full-stack demo application written in Kotlin for [JetBrains Night Moscow 2019](https://info.jetbrains.com/jetbrains-night-moscow-2019).

## Description

This application displays a feed containing user-generated posts and comments. All data is stubbed by the fakeJSON and JSON Placeholder services.

It is a [Kotlin Multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html) project.

It uses:
- [kotlin-multiplatform](https://github.com/JetBrains/kotlin/blob/770a2e3f2d337b84f72dd574d6564913e065b58d/libraries/tools/kotlin-gradle-plugin/src/main/kotlin/org/jetbrains/kotlin/gradle/plugin/KotlinMultiplatformPlugin.kt) plugin for Kotlin compilation;
- [kotlin-frontend-plugin](https://github.com/Kotlin/kotlin-frontend-plugin) for frontend configuration;
- [Ktor framework](https://ktor.io) as a web server;
- [H2](http://www.h2database.com/html/main.html) in-memory database with [Exposed](https://github.com/JetBrains/Exposed) to preload posts at application startup;
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) for client/server (de-)serialization and RPC;
- [React](https://reactjs.org), [Styled components](https://www.styled-components.com), [Ring UI 2.0](https://jetbrains.github.io/ring-ui/develop-2.0/index.html) and [kotlin-wrappers](https://github.com/JetBrains/kotlin-wrappers) for rendering. 

During application startup posts are preloaded to the in-memory database from the fakeJSON (or JSON Placeholder) service. 
When the user requests the page, several posts are selected from the DB and the corresponding comments are fetched via the multi-platform HTTP client.
Posts with comments are served to the client via RPC. After the initial render is done, information about authors is fetched via the client-side HTTP client and rendered after all coroutines terminate successfully.
The "Load more comments" button fetches additional comments for the post using the multi-platform HTTP client.

This application has no error handling and has very limited RPC serialization. It's not meant for production use and serves only as a technology example.

### Useful Gradle tasks
`gradle run` runs webpack-dev-server and the Ktor application. You can find logs at `build/logs`.

`gradle stop` stops webpack-dev-server and the Ktor application.

`gradle bundle` to create static files bundle.

`gradle webpack-run` runs webpack-dev-server only.

`gradle webpack-stop` stops webpack-dev-server only.

`gradle ktor-run` runs the Ktor application only.

`gradle ktor-stop` stops the Ktor application only.

`gradle jvmTest` to run common and JVM tests with JUnit.

`gradle runKarmaTests` to run common and JS tests with Mocha and Karma.

### Hosts
webpack-dev-server responds at http://0.0.0.0:8080, the Ktor application responds at http://0.0.0.0:8081.

### Contact information

Feel free to contact me via:
- Mikhail.Kraynov@jetbrains.com
- [Kotlin Slack channel](https://kotlin.slack.com)
- GitHub issues

Pull requests are welcome!
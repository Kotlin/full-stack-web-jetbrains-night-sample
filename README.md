# kotlin-full-stack-application-demo

Full stack application demo written in Kotlin for JetBrains Night Moscow 2019.

### Useful Gradle tasks
`gradle run` runs webpack dev server and ktor application. You could find logs at `build/logs`.

`gradle stop` stops webpack dev server and ktor application.

`gradle bundle` to create static files bundle.

`gradle webpack-run` runs webpack dev server only.

`gradle webpack-stop` stops webpack dev server only.

`gradle ktor-run` runs ktor application only.

`gradle ktor-stop` stops ktor application only.

`gradle jvmTest` to run common and jvm tests with JUnit.

### Hosts
Webpack dev server responding at http://0.0.0.0:8080. Ktor application responding at http://0.0.0.0:8081.

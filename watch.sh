#!/usr/bin/env bash

set +x
set -e

cd ~/kotlin
./gradlew :kotlin-gradle-plugin:install --parallel

cd /Users/jetbrains/IdeaProjects/kotlin-full-stack-application-demo
./gradlew -Dorg.gradle.debug=true $@ --continuous
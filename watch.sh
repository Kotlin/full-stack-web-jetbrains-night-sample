#!/usr/bin/env bash

set +x
set -e

cd ~/kotlin
./gradlew :kotlin-gradle-plugin:install --parallel

cd - # back to previous dir (project dir)
./gradlew -Dorg.gradle.debug=true $@ --continuous
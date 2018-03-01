version = "1.3.1"

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(files("libs/org.json.jar"))
    implementation(project(":InternetRequest"))
}
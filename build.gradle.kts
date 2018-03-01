import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
buildscript {
    var kotlinVersion : String by extra
    kotlinVersion = "1.2.21"

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", kotlinVersion))
    }
}

val kotlinVersion : String by extra

apply {
    plugin("kotlin")
    plugin("maven")
}

plugins {
    kotlin("jvm") version "1.2.21"
}

allprojects {
    group = "com.github.HoshinoTented"

    apply {
        plugin("kotlin")
    }

    plugins {
        kotlin("jvm")
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8", kotlinVersion))
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
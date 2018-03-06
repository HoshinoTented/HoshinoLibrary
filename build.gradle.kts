import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
buildscript {
    var kotlinVersion : String by extra
    kotlinVersion = "1.2.30"

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
}

plugins {
    kotlin("jvm") version "1.2.30"
}

allprojects {
    group = "com.github.HoshinoTented"

    apply {
        plugin("kotlin")
    }

    plugins {
        kotlin("jvm")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8", kotlinVersion))

        testImplementation("junit", "junit", "4.12")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
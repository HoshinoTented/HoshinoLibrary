import org.gradle.api.internal.HasConvention
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

/*I LOVE KOTLIN*/

var kotlinVersion : String by extra
kotlinVersion = "1.2.40"

group = "com.github.HoshinoTented"
version = "1.0.8-04"

plugins {
	kotlin("jvm") version "1.2.40"
	maven
}

val SourceSet.kotlin get() = (this as HasConvention).convention.getPlugin(KotlinSourceSet::class.java).kotlin
val Project.sourceSets get() = java.sourceSets

allprojects {
	apply {
		plugin("kotlin")
		plugin("maven")
	}

	repositories {
		mavenCentral()
	}

	dependencies {
		implementation(kotlin("stdlib-jdk8", kotlinVersion))
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions.jvmTarget = "1.8"
	}

	val sourcesJar = task<Jar>("sourcesJar") {
		from(sourceSets.getByName("main").allSource)
		classifier = "sources"
	}

	artifacts {
		add("archives", sourcesJar)
	}
}
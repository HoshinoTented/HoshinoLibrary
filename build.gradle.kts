import org.gradle.api.internal.HasConvention
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

/* I LOVE KOTLIN */

var kotlinVersion : String by extra
kotlinVersion = "1.2.51"

group = "com.github.HoshinoTented"
version = "1.0.91"

plugins {
	kotlin("jvm") version "1.2.41"
	maven
}

val SourceSet.kotlin get() = (this as HasConvention).convention.getPlugin(KotlinSourceSet::class.java).kotlin
val Project.sourceSets : SourceSetContainer get() = java.sourceSets

allprojects {
	apply {
		plugin("kotlin")
		plugin("maven")
	}

	repositories {
		jcenter()
	}

	dependencies {
		implementation(kotlin("stdlib-jdk8", kotlinVersion))
		testImplementation(kotlin("test-junit", kotlinVersion))
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions.jvmTarget = "1.8"
	}

	val sourcesJar = task<Jar>("sourcesJar") {
		from(sourceSets.getByName("main").kotlin)
		classifier = "sources"
	}

	artifacts {
		operator fun String.invoke(obj : Any) = add(this, obj)
		"archives"(sourcesJar)
	}
}
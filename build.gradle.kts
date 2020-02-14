import org.gradle.api.internal.HasConvention
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

/* I LOVE KOTLIN */
plugins {
	kotlin("jvm") version "1.3.61"
	id("com.jfrog.bintray") version "1.8.4"
	maven
	java
	`maven-publish`
}

val bintray_key: String by extra
var kotlinVersion : String by extra
kotlinVersion = "1.3.61"

group = "org.hoshino9"
version = "1.0.91"

val SourceSet.kotlin get() = (this as HasConvention).convention.getPlugin(KotlinSourceSet::class.java).kotlin
//val Project.sourceSets : SourceSetContainer get() = java.sourceSets

allprojects {
	apply {
		plugin("kotlin")
		plugin("maven")
		plugin("java")
		plugin("com.jfrog.bintray")
		plugin("maven-publish")
	}

	version = rootProject.version

	repositories {
		maven("https://maven.aliyun.com/repository/public")
		jcenter()
	}

	dependencies {
		implementation(kotlin("stdlib", kotlinVersion))
		testImplementation(kotlin("test-junit", kotlinVersion))
	}

	sourceSets {
		getByName("main") {
			withConvention(KotlinSourceSet::class) {
				kotlin.srcDirs("src/main/gen")
			}
		}
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions.jvmTarget = "1.8"
	}

	val cleanGen = task<Delete>("cleanGen") {
		delete("src/main/gen")
	}

	val sourcesJar = task<Jar>("sourcesJar") {
		from(sourceSets.getByName("main").kotlin)
		archiveClassifier.set("sources")
	}

	artifacts {
		operator fun String.invoke(obj : Any) = add(this, obj)
		"archives"(sourcesJar)
	}

	tasks["clean"].dependsOn(cleanGen)
}

subprojects {
	bintray {
		user = "hoshinotented"
		key = bintray_key
		setPublications("maven")
		setConfigurations("archives")

		pkg.apply {
			name = rootProject.name
			repo = "hoshino9"
			setLicenses("MIT")
			publicDownloadNumbers = true
			vcsUrl = "https://github.com/HoshinoTented/HoshinoLibrary.git"
			version.apply {
				vcsTag = rootProject.version.toString()
				name = vcsTag
				websiteUrl = "https://github.com/HoshinoTented/HoshinoLibrary/releases/tag/$vcsTag"
			}
		}
	}

	publishing {
		publications {
			create<MavenPublication>("maven") {
				from(components["java"])
				groupId = rootProject.group.toString()
				artifactId = "${rootProject.name.toLowerCase()}-${project.name.toLowerCase()}"
				version = rootProject.version.toString()

				artifact(tasks["sourcesJar"])

				pom.withXml {
					val root = asNode()
					root.appendNode("description", "Hoshino Library")
					root.appendNode("name", project.name)
					root.appendNode("url", "https://github.com/HoshinoTented/HoshinoLibrary")
					root.children().last()
				}
			}
		}
	}
}

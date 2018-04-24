import org.gradle.api.internal.HasConvention
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

dependencies {
	implementation("com.google.code.gson:gson:2.8.2")
	implementation("org.json:json:20180130")
}

val generatorsPackage = "top.tented.utils.generators"
val sourceSets = java.sourceSets
val SourceSet.kotlin get() = (this as HasConvention).convention.getPlugin(KotlinSourceSet::class.java).kotlin

sourceSets {
	"main" {
		kotlin.srcDirs += file("src/main/gen")
	}
}

val genShortDiv = task<JavaExec>("genShortDiv") {
	classpath = sourceSets.getByName("main").runtimeClasspath
	main = "$generatorsPackage.ShortDivGenerator"
	println("Generated ShortDivGenerator")
}

task("genAll") {
	dependsOn(genShortDiv)
}
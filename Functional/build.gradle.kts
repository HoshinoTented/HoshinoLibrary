import org.gradle.api.internal.HasConvention
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

val sourceSets : SourceSetContainer = java.sourceSets
val SourceSet.kotlin get() = (this as HasConvention).convention.getPlugin(KotlinSourceSet::class.java).kotlin

sourceSets {
	"main" {
		kotlin.srcDir("src/main/gen")
	}
}

val generatorsPackage = "org.hoshino9.generators"
val genList : List<String> = listOf(
	"CurringGenerator"
)

task("genAll") {
	genList.forEach {
		createTask(it, JavaExec::class) {
			classpath = sourceSets.getByName("main").runtimeClasspath
			main = "$generatorsPackage.$name"
		}.let { dependsOn(it) }
	}
}
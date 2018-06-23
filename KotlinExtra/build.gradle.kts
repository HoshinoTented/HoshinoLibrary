import org.gradle.api.internal.HasConvention
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

val generatorsPackage = "top.tented.utils.generators"
val sourceSets : SourceSetContainer = java.sourceSets
val SourceSet.kotlin get() = (this as HasConvention).convention.getPlugin(KotlinSourceSet::class.java).kotlin

sourceSets {
	"main" {
		kotlin.srcDir("src/main/gen")
	}
}

val genShortDiv = task<JavaExec>("genShortDiv") {
	classpath = sourceSets.getByName("main").runtimeClasspath
	main = "$generatorsPackage.ShortDivGenerator"
}

val genList : List<Task> = listOf(
		genShortDiv
)

task("genAll") {
	genList.forEach {
		dependsOn(it)
	}
}
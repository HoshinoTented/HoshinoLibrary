import org.gradle.api.internal.HasConvention
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

dependencies {
	compileOnly("com.google.code.gson:gson:2.8.2")
	compileOnly("org.json:json:20180130")
}

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
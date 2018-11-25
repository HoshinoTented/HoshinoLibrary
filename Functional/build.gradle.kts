import org.hoshino9.gradle.generator.*
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

sourceSets {
	getByName("main") {
		withConvention(KotlinSourceSet::class) {
			kotlin.srcDir("src/main/gen")
		}
	}
}

val compileKotlin = tasks["compileKotlin"] as KotlinCompile
val genCurring = task<CurringGenerator>("genCurring")
val genUncurring = task<UncurringGenerator>("genUncurring")

compileKotlin.dependsOn(genCurring, genUncurring)
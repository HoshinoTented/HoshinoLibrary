import org.gradle.api.internal.HasConvention
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.hoshino9.gradle.generator.*

val compileKotlin = tasks["compileKotlin"] as KotlinCompile
val genShortDiv = task<ShortDivGenerator>("genShortDiv")

compileKotlin.dependsOn(genShortDiv)
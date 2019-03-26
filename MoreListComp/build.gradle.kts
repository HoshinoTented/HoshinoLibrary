import org.hoshino9.gradle.generator.MoreListCompGenerator

val genMoreListComp = task<MoreListCompGenerator>("genMoreListComp")

tasks["compileKotlin"].dependsOn(genMoreListComp)
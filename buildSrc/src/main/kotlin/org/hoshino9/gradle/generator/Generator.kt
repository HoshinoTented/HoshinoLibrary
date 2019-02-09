package org.hoshino9.gradle.generator

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.intellij.lang.annotations.Language

abstract class Generator : DefaultTask(), Runnable {
	abstract val packageName : String
	abstract val fileName : String

	val header by lazy {
		"""@file:Suppress("unused")

package org.hoshino9.$packageName
		"""
	}

	@TaskAction
	override fun run() {
		val targetFile = project
			.projectDir
			.resolve("src")
			.resolve("main")
			.resolve("gen")
			.resolve("org")
			.resolve("hoshino9")
			.resolve(packageName)
			.resolve("$fileName.kt")
			.absoluteFile

		targetFile.parentFile.mkdirs()

		buildString {
			appendln(header)
			append(gen())
		}.let { targetFile.writeText(it) }
	}

	abstract fun gen() : String
}
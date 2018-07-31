package org.hoshino9.generators

import org.intellij.lang.annotations.Language
import org.hoshino9.utils.yesOrNo
import java.io.File

const val genPath = "src/main/gen"

/**
 * 短除 => 返回`商`和`余`
 */
internal object ShortDivGenerator {
	private const val output = "$genPath/org/hoshino9/utils/_Math\$ShortDiv.kt"
	@Language("Kotlin") private const val header = """@file:Suppress("unused")

package org.hoshino9.utils
"""

	enum class Type {
		Byte,
		Short,
		Int,
		Long,
		Float,
		Double
	}

	private fun gen() {
		val outputFile = output.run(::File).apply {
			if (exists().not()) {
				parentFile.mkdirs()
				createNewFile()
			}
		}

		buildString {
			appendln(header)        //add header

			Type.values().forEach { type ->
				Type.values().forEach { other ->
					val returnType = (type < other).yesOrNo(other, type).takeUnless { it == Type.Byte || it == Type.Short } ?: Type.Int
					@Language("Kotlin") val doc = """/**
 * This is comment, I don`t know what should write here
 */"""
					@Language("Kotlin") val code = "infix fun $type.shortDiv(other : $other) : Pair<$returnType, $returnType> = div(other) to rem(other)\n"
					appendln(doc)
					appendln(code)
				}
			}
		}.let { outputFile.writeText(it) }
	}

	@JvmStatic
	fun main(args : Array<String>) {
		gen()
		println("Generated Short Div")
	}
}
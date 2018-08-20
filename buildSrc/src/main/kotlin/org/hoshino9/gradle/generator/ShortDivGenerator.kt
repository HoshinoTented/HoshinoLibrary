package org.hoshino9.gradle.generator

import org.intellij.lang.annotations.Language
import java.io.File

/**
 * 短除 => 返回`商`和`余`
 */
open class ShortDivGenerator : Generator() {
	enum class Type {
		Byte,
		Short,
		Int,
		Long,
		Float,
		Double
	}

	override val fileName : String = "shortDiv"
	override val packageName : String = "utils"

	override fun gen() : String {
		return buildString {
			Type.values().forEach { type ->
				Type.values().forEach { other ->
					val returnType = if (type < other) { other } else { type }.takeUnless { it == Type.Byte || it == Type.Short } ?: Type.Int
					@Language("Kotlin") val doc = """/**
 * This is comment, I don`t know what should write here
 */"""
					@Language("Kotlin") val code = "infix fun $type.shortDiv(other : $other) : Pair<$returnType, $returnType> = div(other) to rem(other)\n"
					appendln(doc)
					appendln(code)
				}
			}
		}
	}
}
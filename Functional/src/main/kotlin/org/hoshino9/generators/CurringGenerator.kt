package org.hoshino9.generators

import org.intellij.lang.annotations.Language
import java.io.File

internal object CurringGenerator {
	private const val equal = "="
	private const val lambda = "->"
	private const val lambdaPrefix = "{"
	private const val lambdaPostfix = "}"
	private const val space = " "
	private const val functionKeyword = "fun"
	private const val parameterName = "f"
	private const val parameterPrefix = "("
	private const val parameterPostfix = ")"
	private const val typeChar = ":"
	private const val typePrefix = "<"
	private const val typePostfix = ">"
	private const val returnType = "R"
	private const val postfix = "kt"
	private const val functionName = "curring"
	private const val genPath = "src/main/gen"
	private const val genPackage = "org/hoshino9/functional"
	@Language("Kotlin") private const val beforeCode = """@file:Suppress("unused")

package org.hoshino9.functional
"""
	private const val maxParameterCount = 22
	private val outputFile get() = File("$genPath/$genPackage/_Functional\$Curring.$postfix")

	private fun comment(i : Int) : String = """/** A curring funtion that takes $i argument${if (i > 1) "s" else ""} */"""
	private fun parameter(i : Int) : String = "p$i"
	private fun type(i : Int) : String = "P$i"
	private fun body(i : Int, max : Int) : String {
		return buildString {
			if (i == max) {
				append(parameterName)
				(1..max).joinTo(this, prefix = parameterPrefix, postfix = parameterPostfix, transform = ::parameter)
			} else {
				append(lambdaPrefix)
				append(space)
				append(parameter(i + 1))
				append(space)
				append(lambda)
				append(space)
				append(body(i + 1, max))
				append(space)
				append(lambdaPostfix)
			}
		}
	}

	private fun gen() : String {
		return buildString {
			appendln(beforeCode)        //添加代码前内容

			(0..maxParameterCount).forEach {
				appendln(comment(it))
				buildString {
					val types = (0 until it).joinToString(transform = ::type)
					val parameterType = buildString {
						append(parameterPrefix)
						append(types)
						append(parameterPostfix)
						append(space)
						append(lambda)
						append(space)
						append(returnType)
					}

					val returnLambdaType = buildString {
						0.until(it).joinTo(this, separator = "") {
							" (${type(it)}) $lambda"
						}

						append(space)
						append(returnType)
					}

					append(functionKeyword)

					append(space)
					append(typePrefix)
					append(types)
					if (types != "") {
						append(", ")
					}

					append(returnType)
					append(typePostfix)
					append(space)

					append(functionName)

					append(parameterPrefix)
					append(parameterName)
					append(typeChar)
					append(space)
					append(parameterType)
					append(parameterPostfix)

					append(typeChar)

					append(returnLambdaType)
					append(space)

					append(equal)
					append(space)
					appendln(body(0, it))
				}.run(::appendln)
			}
		}
	}

	@JvmStatic
	fun main(args : Array<String>) {
		outputFile.apply {
			if (exists().not()) {
				parentFile.mkdirs()
				createNewFile()
			}

			writeText(gen().apply(::println))
		}
	}
}
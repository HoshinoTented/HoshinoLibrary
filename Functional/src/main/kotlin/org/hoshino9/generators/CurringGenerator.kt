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
	private const val curringName = "curring"
	private const val uncurringName = "uncurring"
	private const val genPath = "src/main/gen"
	private const val genPackage = "org/hoshino9/functional"
	@Language("Kotlin") private const val beforeCode = """@file:Suppress("unused")

package org.hoshino9.functional
"""
	private const val maxParameterCount = 22
	private val curringOutput get() = File("$genPath/$genPackage/_Functional\$Curring.$postfix")
	private val uncurringOutput get() = File("$genPath/$genPackage/_Functional\$Uncurring.$postfix")

	private fun comment(i : Int, isCurring : Boolean) : String = """/** A ${if (isCurring.not()) "un" else ""}curring funtion that takes $i argument${if (i > 1) "s" else ""} */"""
	private fun parameter(i : Int) : String = "p$i"
	private fun type(i : Int) : String = "P$i"
	private fun types(i : Int) : String = (0 until i).joinToString(transform = ::type)
	private fun bodyCurring(max : Int) : String = _bodyCurring(0, max)
	private fun _bodyCurring(i : Int, max : Int) : String {
		return buildString {
			if (i == max) {
				append(parameterName)
				if (1 <= max) (0 until max)
					.joinTo(
						this,
						prefix = parameterPrefix,
						postfix = parameterPostfix,
						transform = ::parameter)
			} else {
				append(lambdaPrefix)
				append(space)
				append(parameter(i))
				append(space)
				append(lambda)
				append(space)
				append(_bodyCurring(i + 1, max))
				append(space)
				append(lambdaPostfix)
			}
		}
	}

	private fun bodyUncurring(max : Int) : String {
		return buildString {
			if (max == 0) {
				append(parameterName)
			} else {
				append(lambdaPrefix)
				append(space)
				if (1 <= max) (1..max).joinTo(this, postfix = " $lambda ", transform = ::parameter)
				append(parameterName)
				(1..max).joinTo(this, separator = "") { "$parameterPrefix${parameter(it)}$parameterPostfix" }

				append(space)
				append(lambdaPostfix)
			}
		}
	}

	private fun uncurringParameters(it : Int) : String {
		return buildString {
			append(parameterPrefix)
			append(types(it))
			append(parameterPostfix)
			append(space)
			append(lambda)
			append(space)
			append(returnType)
		}
	}

	private fun curringParameters(it : Int) : String {
		return buildString {
			if (it == 0) {
				append("() -> R")
			} else {
				0.until(it).joinTo(this, separator = " $lambda ", postfix = " $lambda $returnType") {
					"(${type(it)})"
				}
			}
		}
	}

	private fun gen(isCurring : Boolean, fParameterType : (Int) -> String, fReturnLambdaType : (Int) -> String, functionBody : (Int) -> String) : String {
		return buildString {
			appendln(beforeCode)        //添加代码前内容

			(0..maxParameterCount).forEach {
				appendln(comment(it, isCurring))
				buildString {
					val types = types(it)
					val parameterType = fParameterType(it)
					val returnLambdaType = fReturnLambdaType(it)

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

					append(if (isCurring) curringName else uncurringName)

					append(parameterPrefix)
					append(parameterName)
					append(typeChar)
					append(space)
					append(parameterType)
					append(parameterPostfix)

					append(typeChar)

					append(space)
					append(returnLambdaType)
					append(space)

					append(equal)
					append(space)
					appendln(functionBody(it))
				}.run(::appendln)
			}
		}
	}

	private fun genUncurring() : String {
		return gen(false, ::curringParameters, ::uncurringParameters, ::bodyUncurring)
	}
	private fun genCurring() : String {
		return gen(true, ::uncurringParameters, ::curringParameters, ::bodyCurring)
	}

	@JvmStatic
	fun main(args : Array<String>) {
		mapOf(curringOutput to ::genCurring, uncurringOutput to ::genUncurring).forEach { (file, generator) ->
			if (file.exists().not()) {
				file.parentFile.mkdirs()
				file.createNewFile()
			}

			file.writeText(generator().apply(::println))
		}
	}
}
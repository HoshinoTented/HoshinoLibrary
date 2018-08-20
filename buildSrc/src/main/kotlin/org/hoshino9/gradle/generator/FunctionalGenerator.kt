package org.hoshino9.gradle.generator

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
private const val curringName = "curring"
private const val uncurringName = "uncurring"
private const val maxParameterCount = 22

abstract class CurringBaseGenerator : Generator() {
	override val packageName : String = "functional"

	private fun comment(i : Int, isCurring : Boolean) : String = """/** A ${if (isCurring.not()) "un" else ""}curring funtion that takes $i argument${if (i > 1) "s" else ""} */"""
	protected fun parameter(i : Int) : String = "p$i"
	private fun type(i : Int) : String = "P$i"
	private fun types(i : Int) : String = (0 until i).joinToString(transform = ::type)

	protected fun uncurringParameterType(it : Int) : String {
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

	protected fun curringParameterType(it : Int) : String {
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

	protected fun gen(isCurring : Boolean, fParameterType : (Int) -> String, fReturnLambdaType : (Int) -> String, functionBody : (Int) -> String) : String {
		return buildString {
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
}

open class CurringGenerator : CurringBaseGenerator() {
	override val fileName : String = "curring"

	private fun bodyCurring(max : Int) : String = bodyCurring(0, max)
	private fun bodyCurring(i : Int, max : Int) : String {
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
				append(bodyCurring(i + 1, max))
				append(space)
				append(lambdaPostfix)
			}
		}
	}

	override fun gen() : String {
		return this.gen(true, ::uncurringParameterType, ::curringParameterType, ::bodyCurring)
	}
}

open class UncurringGenerator : CurringBaseGenerator() {
	override val fileName : String = "uncurring"

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

	override fun gen() : String {
		return this.gen(false, ::curringParameterType, ::uncurringParameterType, ::bodyUncurring)
	}
}
package org.hoshino9.gradle.generator

internal const val equal = "="
internal const val lambda = "->"
internal const val lambdaPrefix = "{"
internal const val lambdaPostfix = "}"
internal const val space = " "
internal const val functionKeyword = "fun"
internal const val typealiasKeyword = "typealias"
internal const val parameterName = "f"
internal const val parameterPrefix = "("
internal const val parameterPostfix = ")"
internal const val typeChar = ":"
internal const val typePrefix = "<"
internal const val typePostfix = ">"
internal const val returnType = "R"
internal const val curringName = "curring"
internal const val uncurringName = "uncurring"
internal const val curriedTypeName = "CurriedFunction"
internal const val uncurriedTypeName = "UncurriedFunction"
internal const val maxParameterCount = 22

internal fun comment(i : Int, isCurring : Boolean) : String = """/** A ${if (isCurring.not()) "un" else ""}curring function that takes $i argument${if (i > 1) "s" else ""} */"""

// p1
internal fun parameter(i : Int) : String = "p$i"

// P1
internal fun type(i : Int) : String = "P$i"

// P1, P2, P3
internal fun types(i : Int) : String = (0 until i).joinToString(transform = ::type)

// (A, B) -> C
internal fun uncurriedParameterType(it : Int) : String {
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

// (A) -> (B) -> C
internal fun curriedParameterType(it : Int) : String {
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

abstract class BaseGenerator : Generator() {
	override val packageName : String = "functional"
}

abstract class CurringBaseGenerator : BaseGenerator() {
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
		return super.gen(true, ::uncurriedParameterType, ::curriedParameterType, ::bodyCurring)
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
		return super.gen(false, ::curriedParameterType, ::uncurriedParameterType, ::bodyUncurring)
	}
}

abstract class BaseTypeAliasGenerator : BaseGenerator() {
	protected fun gen(prefixName : String, generics : (Int) -> String, readType : (Int) -> String) : String {
		return buildString {
			(0..maxParameterCount).forEach {
				append(typealiasKeyword)
				append(space)
				append(prefixName)
				append(it)
				append(typePrefix)
				append(generics(it))
				if (it > 0) append(", ")
				append("R")
				append(typePostfix)
				append(space)
				append(equal)
				append(space)
				appendln(readType(it))
			}
		}
	}
}

open class CurriedTypeGenerator : BaseTypeAliasGenerator() {
	override val fileName : String = "curried-typealias"

	override fun gen() : String {
		return gen(curriedTypeName, ::types, ::curriedParameterType)
	}
}

open class UncurriedTypeGenerator : BaseTypeAliasGenerator() {
	override val fileName : String = "uncurried-typealias"

	override fun gen() : String {
		return gen(uncurriedTypeName, ::types, ::uncurriedParameterType)
	}
}
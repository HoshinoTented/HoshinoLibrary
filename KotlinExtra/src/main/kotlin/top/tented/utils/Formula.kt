@file:Suppress("unused")

package top.tented.utils

interface Evaluable {
	operator fun invoke() : Double
}

class EvalNumber(private val value : Double) : Evaluable {
	override fun invoke() : Double {
		return value
	}
}

class EvalOperator(private val operator : Operator, private val first : Evaluable, private val last : Evaluable) : Evaluable {
	companion object {
		enum class Operator {
			PLUS,
			MINUS,
			TIMES,
			DIV
		}

		class Token(val type : Type, val strValue : String) {
			enum class Type {
				Number,
				Operator,
				END
			}
		}

		class Lexer(private val source : String) {
			companion object {
				private const val END = '\u0000'
				private const val OPERATOR = "+-*/()"
				private const val NUMBER = "0123456789."
			}

			private var currentIndex : Int = 0
			private val currentChar get() = source.getOrNull(currentIndex) ?: END

			private fun next() = currentIndex ++

			private fun readFullNumber() : String = buildString {
				while (currentChar in NUMBER) {
					append(currentChar)
					next()
				}
			}

			private fun lexNumber() : Token = Token(Token.Type.Number, readFullNumber())
			private fun lexOperator() : Token = Token(Token.Type.Operator, currentChar.toString())

			fun lexFormula() : List<Token> {
				currentIndex = 0

				val tokens = ArrayList<Token>()
				while (currentChar != END) {
					when (currentChar) {
						in NUMBER -> lexNumber()
						in OPERATOR -> lexOperator()

						else -> throw IllegalArgumentException("Unexpected $currentChar")
					}.let(tokens::add)
				}

				return tokens
			}
		}

		class Parser(private val tokens : List<Token>) {
			private var currentIndex : Int = 0
			private val currentToken get() = tokens.getOrNull(currentIndex) ?: Token(Token.Type.END, "")

			private fun parseBracket() : EvalOperator {
				TODO()
			}

			fun parseFormula() {
				when (currentToken) {

				}
			}
		}
	}

	override fun invoke() : Double {
		val operate : Double.(Double) -> Double = when (operator) {
			Operator.PLUS -> Double::plus
			Operator.MINUS -> Double::minus
			Operator.TIMES -> Double::times
			Operator.DIV -> Double::div
		}

		return operate(first(), last())
	}
}
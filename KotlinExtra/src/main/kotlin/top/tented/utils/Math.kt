package top.tented.utils

import java.util.*
import java.util.Collections.reverse
import kotlin.math.max
import kotlin.math.min

const val levels = "+- */% ()"        //() are always the highest level

fun Char.isSymbol() = this in levels
fun String.isSymbol() = length == 1 && this in levels

fun infix2stack(formula : String) = Stack<String>().apply {
    var builder = StringBuilder()

    formula.forEach {
        if (it.isSymbol()) {
            addNotEmpty(builder.toString())
            add(it.toString())
            builder = StringBuilder()
        } else builder.append(it)
    }

    addNotEmpty(builder.toString())
}

fun compareSymbol(symbol : String, compare : String) : Boolean {
    val symbolIndex = levels.indexOf(symbol)
    val compareIndex = levels.indexOf(compare)

    val max = max(symbolIndex, compareIndex)
    val min = min(symbolIndex, compareIndex)

    return max - 1 > min && max == symbolIndex
}

fun infixOperate(b : Double, infix : String, a : Double) =
        when (infix) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> a / b
            "%" -> a % b

            else -> throw IllegalArgumentException("Symbol can not be $infix")
        }

/**
 * 特殊的(
 * 当压栈的时候, 它的优先级最高
 * 当出栈的时候, 它优先级最低
 * 为什么)不特殊呢, 因为一开始就if掉了啊（逃
 */
fun infix2postfix(infix : List<String>) : Stack<String> {
    val operators = Stack<String>()
    val stack = Stack<String>()

    infix.forEach {
        if (it.isSymbol()) {
            val top = operators.lastOrNull() ?: ""
            if (it != ")") {
                if (top != "(" && compareSymbol(top, it)) {
                    while (operators.isNotEmpty() && operators.peek() != "(") stack.push(operators.pop())
                    operators.push(it)
                } else operators.push(it)
            } else {
                do {
                    var buffer = operators.pop()
                    stack.push(buffer)        //这个时候按照语法不可能是左括号
                    buffer = operators.pop()
                } while (buffer != "(")
            }
        } else stack.push(it)
    }

    while (operators.isNotEmpty()) stack.push(operators.pop())

    return stack
}

fun operateInfix(infix : Stack<String>) : Double {
    val stack = Stack<String>()

    while (infix.isNotEmpty()) {
        val top = infix.pop()

        if (top.isSymbol()) {
            stack.push(infixOperate(stack.pop().toDouble(), top, stack.pop().toDouble()).toString())
        } else stack.push(top)
    }

    return stack.pop().toDouble()
}

fun operate(formula : String) = operateInfix(infix2postfix(infix2stack(formula)).apply(::reverse))

fun Boolean.toInt() = if (this) 1 else 0
fun Number.toBoolean() = toInt() == 0

/**
 * this 随机到 end(不包含)
 */
infix fun Long.randomTo(end : Long) = this + (Math.random() * (end - this)).toLong()
package top.tented.utils

import java.util.Random
import java.util.regex.Matcher
import java.util.regex.Pattern

operator fun String.get(from : Int, end : Int) =
        substring(
                (if (from < 0) length else 0)
                        + from,
                (if (end < 0) length else 0)
                        + end
        )

operator fun CharSequence.times(times : Int) =
        StringBuilder("").apply {
            repeat(times) {
                append(this@times)
            }
        }.toString()

operator fun CharSequence.div(value : Int) : List<String> = splitWithLength(((this.length / value.toDouble()) + 0.5).toInt())

fun String.toFile(root : String = "" ) = java.io.File(root + this)
fun String.toUrl() = java.net.URL(this)
fun String.toBuilder() = StringBuilder(this)
fun String.toClass() = Class.forName(this)

fun CharSequence.splitWithLength(length : Int) : List<String> =
        if (length > 0) {
            ArrayList<String>().apply {
                var count = 0
                var buffer = StringBuilder()

                this@splitWithLength.forEach {
                    count++
                    buffer.append(it)

                    if (count == length) {
                        add(buffer.toString())
                        buffer = StringBuilder()
                        count = 0
                    }
                }

                if (buffer.isNotEmpty()) add(buffer.toString())
            }
        } else throw StringIndexOutOfBoundsException(length)

private fun CharSequence.match(regex : String, takeIf : Matcher.() -> Boolean) = Pattern.compile(regex).matcher(this).takeIf(takeIf)

fun CharSequence.matches(regex : String) = match(regex, Matcher::matches)
fun CharSequence.find(regex : String) = match(regex, Matcher::find)
fun CharSequence.lookingAt(regex : String) = match(regex, Matcher::lookingAt)

fun CharSequence.randomElement() = this[Random().nextInt(this.length)]

fun CharSequence.isInt() = matches(Regex("-?\\d+"))
fun CharSequence.isFloat() = matches(Regex("-?\\d+\\.\\d+"))
fun CharSequence.isNumber() = isInt() || isFloat()
@file:Suppress("unused")

package top.tented.utils

import org.intellij.lang.annotations.Language
import java.util.Random
import java.util.regex.Matcher
import java.util.regex.Pattern

operator fun String.get(from : Int, end : Int) = substring(
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

fun CharSequence.firstUpperCase() = this[0].toUpperCase().toString() + subSequence(1..length)
fun CharSequence.insert(index : Int, str : CharSequence) = substring(0, index) + str + substring(index)

fun CharSequence.splitWithLength(length : Int) : List<String> =
		if (length > 0) {
			ArrayList<String>().apply {
				var count = 0
				var buffer = StringBuilder()

				this@splitWithLength.forEach {
					count ++
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

private fun CharSequence.match(@Language("RegExp") regex : String, takeIf : Matcher.() -> Boolean) = Pattern.compile(regex).matcher(this).takeIf(takeIf)

fun CharSequence.matches(@Language("RegExp") regex : String) = match(regex, Matcher::matches)
fun CharSequence.find(@Language("RegExp") regex : String) = match(regex, Matcher::find)
fun CharSequence.lookingAt(@Language("RegExp") regex : String) = match(regex, Matcher::lookingAt)

fun CharSequence.randomElement() = this[Random().nextInt(this.length)]
fun CharSequence.cut(from : Int, length : Int) = substring(from, from + length)

fun String.replace(replaces : Map<String, String>) = replaces.forEach { old, new -> replace(old, new) }
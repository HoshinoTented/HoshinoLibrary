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

operator fun CharSequence.times(times : Int) = repeat(times)
operator fun CharSequence.div(value : Int) = chunked(value)

fun CharSequence.firstUpperCase() = this[0].toUpperCase().toString() + subSequence(1..length)
fun CharSequence.insert(index : Int, str : CharSequence) = substring(0, index) + str + substring(index)

private fun CharSequence.match(@Language("RegExp") regex : String, takeIf : Matcher.() -> Boolean) = Pattern.compile(regex).matcher(this).takeIf(takeIf)

fun CharSequence.matches(@Language("RegExp") regex : String) = match(regex, Matcher::matches)
fun CharSequence.find(@Language("RegExp") regex : String) = match(regex, Matcher::find)
fun CharSequence.lookingAt(@Language("RegExp") regex : String) = match(regex, Matcher::lookingAt)

fun CharSequence.randomElement() = this[Random().nextInt(this.length)]
fun CharSequence.cut(from : Int, length : Int) = substring(from, from + length)

fun String.replaces(replaces : Map<String, String>) = replaces.forEach { old, new -> replace(old, new) }
fun String.replacesRegex(replaces : Map<Regex, String>) = replaces.forEach { reg, new -> replace(reg, new) }
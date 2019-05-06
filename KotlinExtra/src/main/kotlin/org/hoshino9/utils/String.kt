@file:Suppress("unused")

package org.hoshino9.utils

import org.intellij.lang.annotations.Language
import java.util.Random
import java.util.regex.Matcher
import java.util.regex.Pattern

@JvmName("sub")
operator fun String.get(from : Int, end : Int) = substring(
		(if (from < 0) length else 0)
				+ from,
		(if (end < 0) length else 0)
				+ end
)

operator fun CharSequence.times(times : Int) = repeat(times)

fun CharSequence.insert(index : Int, str : CharSequence) = substring(0, index) + str + substring(index)
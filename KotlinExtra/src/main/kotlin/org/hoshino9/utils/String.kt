@file:Suppress("unused")

package org.hoshino9.utils

operator fun CharSequence.times(times : Int) = repeat(times)

fun CharSequence.insert(index : Int, str : CharSequence) = substring(0, index) + str + substring(index)
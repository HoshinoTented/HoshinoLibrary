@file:Suppress("unused")

package org.hoshino9.utils

//这个 `infix` 就没什么意义了呀, 本来就是为了避免 `?:` 的时候要搞个括号框住的
fun <T> T?.orElse(other : T) = this ?: other

@Deprecated("Please not use this function", ReplaceWith("!!"))
fun <T> T?.orNPE() = this ?: throw NullPointerException("Some things went wrong...")

fun <T> T.another(a : T, b : T) = if (a == this) b else a

inline fun <reified R> Any?.cast() = this as? R
inline fun <reified R> Any?.uncheckCast() = this as R
inline fun <reified R> Any?.instanceOf() = this is R
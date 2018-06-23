@file:Suppress("unused")

package org.hoshino9.utils

//这个 `infix` 就没什么意义了呀, 本来就是为了避免 `?:` 的时候要搞个括号框住的
fun <T> T?.or(other : T) = this ?: other

@Deprecated("Please not use this function", ReplaceWith("!!"))
fun <T> T?.orNPE() = this ?: throw NullPointerException("Some things went wrong...")

inline fun <T> T.applyAny(lambda : T.() -> Any?) = apply { lambda() }
inline fun <T> T.alsoAny(lambda : (T) -> Any?) = also { lambda(it) }

inline fun <T> T?.nullCheck(lambda : () -> Unit) = this?.let { lambda() }

fun <T> T.another(a : T, b : T) = if (a == this) b else a
fun <T> T.others(vararg obj : T) = obj.toMutableList().apply { removeAll { it == this@others } }.toList()

inline fun <reified R> Any?.cast() = this as? R
inline fun <reified R> Any?.uncheckCast() = this as R
inline fun <reified R> Any?.instanceOf() = this is R

fun Any.returnUnit() = Unit
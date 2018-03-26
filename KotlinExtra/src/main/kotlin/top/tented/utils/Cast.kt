@file:Suppress("UNCHECKED_CAST")

package top.tented.utils

fun <R> Any?.cast(lambda : Any?.() -> R) = lambda()
fun <R> Any?.cast() = this as? R
fun <R> Any?.uncheckCast() = this as R
inline fun <reified R> Any?.instanceOf() = this is R
@file:Suppress("UNCHECKED_CAST")

package top.tented.utils

fun <T, R> T.cast(lambda : T.() -> R) = this.lambda()
fun <T, R> T.cast() = this as? R
fun <T, R> T.uncheckCast() = this as R
inline fun <T, reified R> T.instanceOf() = this is R
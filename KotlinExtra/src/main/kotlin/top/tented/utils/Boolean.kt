@file:Suppress("unused")

package top.tented.utils

fun Boolean?.orTrue() = or(true)
fun Boolean?.orFalse() = or(false)
fun <T> Boolean.yesOrNull(whenTrue : T) = if (this) whenTrue else null
fun <T> Boolean.yesOrNo(whenTrue : T, whenFalse : T) = yesOrNull(whenTrue) ?: whenFalse
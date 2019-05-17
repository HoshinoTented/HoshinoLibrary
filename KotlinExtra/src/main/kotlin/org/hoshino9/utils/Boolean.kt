@file:Suppress("unused")

package org.hoshino9.utils

fun Boolean?.orTrue() = orElse(true)
fun Boolean?.orFalse() = orElse(false)
fun <T> Boolean.trueOrNull(whenTrue : T) = if (this) whenTrue else null
fun <T> Boolean.trueOrElse(whenTrue : T, whenFalse : T) = trueOrNull(whenTrue) ?: whenFalse

fun Boolean.toBoolean() = this
fun Int.toBoolean() = equals(0).not()
fun Any?.toBoolean() = this != null
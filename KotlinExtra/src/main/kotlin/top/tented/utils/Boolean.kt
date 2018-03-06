package top.tented.utils

fun Any?.equalsOrSelf(other : Any?) = equalsOrNull(other) ?: this
fun Any?.equalsOrNull(other : Any?) = takeIf { it == other }
fun Boolean?.orFalse() = true == this

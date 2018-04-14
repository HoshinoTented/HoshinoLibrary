@file:Suppress("unused")

package top.tented.utils

fun <T> T?.or(other : T) = this ?: other

inline fun <T> T.applyAny(lambda : T.() -> Any?) = apply { lambda() }
inline fun <T> T.alsoAny(lambda : (T) -> Any?) = also { lambda(it) }

inline fun Any?.nullCheck(lambda : () -> Unit) = this?.let { lambda() }

fun <T> T.another(a : T, b : T) = if (a == this) b else a
fun <T> T.others(vararg obj : T) = obj.toMutableList().apply { removeAll { it == this@others } }.toList()
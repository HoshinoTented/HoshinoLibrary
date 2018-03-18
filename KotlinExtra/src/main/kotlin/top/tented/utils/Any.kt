package top.tented.utils

fun <T> T?.or(other : T) = this ?: other

inline fun <T> T.applyAny(lambda : T.() -> Any?) = apply { lambda() }
inline fun <T> T.alsoAny(lambda : (T) -> Any?) = also { lambda(it) }
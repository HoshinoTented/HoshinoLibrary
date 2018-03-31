@file:Suppress("UNCHECKED_CAST")

package top.tented.utils

inline fun <reified R> Any?.cast() = this as? R
inline fun <reified R> Any?.uncheckCast() = this as R
inline fun <reified R> Any?.instanceOf() = this is R
@file:Suppress("unused")

package org.hoshino9.utils

inline fun <T> Iterable<T>.containsIf(condition : (T) -> Boolean) = indexOfFirst(condition) > - 1
inline fun <T> List<T>.containsIf(condition : (T) -> Boolean) = indexOfFirst(condition) > - 1
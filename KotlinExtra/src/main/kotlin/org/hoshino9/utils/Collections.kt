@file:Suppress("unused")

package org.hoshino9.utils

import java.util.Enumeration

inline fun <T> Iterable<T>.containsIf(condition : (T) -> Boolean) = indexOfFirst(condition) > - 1
inline fun <T> List<T>.containsIf(condition : (T) -> Boolean) = indexOfFirst(condition) > - 1

inline fun <K, V> Map<K, V>.containsIf(condition : (Map.Entry<K, V>) -> Boolean) = any(condition)
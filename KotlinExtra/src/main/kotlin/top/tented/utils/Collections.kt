@file:Suppress("unused")

package top.tented.utils

import java.util.Enumeration

fun <T> MutableList<T>.removeRange(range : IntRange) = range.reversed().forEach { removeAt(it) }
fun <T> MutableList<T>.pop() = last().also { removeAt(lastIndex) }
fun <T> MutableList<T>.popOrNull() = lastOrNull()?.apply { removeAt(lastIndex) }

//Iterable 和 List 的实现看起来一样, 所以就直接Iterable了
inline fun <T> Iterable<T>.containsIf(condition : (T) -> Boolean) = indexOfFirst(condition) > - 1

inline fun <K, V> Map<K, V>.containsIf(condition : (Map.Entry<K, V>) -> Boolean) = any(condition)

fun <T> MutableList<T>.addIf(value : T, lambda : (T) -> Boolean) {
	if(lambda(value)) add(value)
}

inline fun <T> Enumeration<T>.forEach(action : (T) -> Unit) = iterator().forEach(action)
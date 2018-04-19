@file:Suppress("unused")

package top.tented.utils

import java.util.*

fun <T> MutableList<T>.removeRange(range : IntRange) = range.reversed().forEach { removeAt(it) }
fun <T> MutableList<T>.pop() = last().also { removeAt(lastIndex) }
fun <T> MutableList<T>.popOrNull() = lastOrNull()?.apply { removeAt(lastIndex) }

inline fun <T> Iterable<T>.containsIf(condition : (T) -> Boolean) = indexOfFirst(condition) > - 1

fun <T> MutableList<T>.addIf(value : T, lambda : (T) -> Boolean) {
	if(lambda(value)) add(value)
}

fun <T> Collection<T>.randomElement() : T {
	forEach { if(Random().nextBoolean()) return it }
	return last()
}

inline fun <T> Enumeration<T>.forEach(action : (T) -> Unit) = iterator().forEach(action)
package top.tented.utils

import java.util.*

fun <T> MutableList<T>.pop() = last().also { removeAt(lastIndex) }
fun <T> MutableList<T>.popOrNull() = lastOrNull()?.apply { removeAt(lastIndex) }

fun <T> MutableList<T>.addIf( value : T, lambda : (T) -> Boolean ) {
	if(lambda(value)) add(value)
}
fun MutableList<String>.addNotEmpty( value : String ) = addIf(value) { it != "" }

fun <T> Collection<T>.randomElement() : T {
	forEach { if(Random().nextBoolean()) return it }
	return last()
}

inline fun <T> Enumeration<T>.forEach(action : (T) -> Unit) {
	for (it in this) action(it)
}
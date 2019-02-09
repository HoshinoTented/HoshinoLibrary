@file:Suppress("unused")

package org.hoshino9.prelude

fun <T> fromIterator(it : Iterator<T>) : List<T> {
	return when (it.hasNext()) {
		false -> List.Nil
		true -> List.Cons(it.next(), fromIterator(it))
	}
}

fun <T> Iterator<T>.toList() : List<T> {
	return fromIterator(this)
}

fun <T> Iterable<T>.toList() : List<T> {
	return fromIterator(iterator())
}

tailrec fun <T> List<T>.toList(xs : MutableList<T> = ArrayList()) {
	when (this) {
		List.Nil -> Unit
		is List.Cons -> {
			xs.add(value)
			list.toList(xs)
		}
	}
}

fun <T> List<T>.toList(constructor : () -> MutableList<T> = ::ArrayList) : kotlin.collections.List<T> {
	return constructor().apply { toList(this) }
}
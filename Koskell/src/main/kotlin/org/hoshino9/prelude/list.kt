@file:Suppress("unused")

package org.hoshino9.prelude

import kotlin.IndexOutOfBoundsException

fun <T> safeHead(xs : List<T>) : Maybe<T> {
	return when (xs) {
		List.Nil -> Maybe.Nothing
		is List.Cons -> Maybe.Just(xs.value)
	}
}

fun <T> head(xs : List<T>) : T {
	return when (val m = safeHead(xs)) {
		Maybe.Nothing -> throw IndexOutOfBoundsException()
		is Maybe.Just -> m.value
	}
}

tailrec fun <T> safeLast(xs : List<T>) : Maybe<T> {
	return when(xs) {
		List.Nil -> Maybe.Nothing
		is List.Cons -> if (xs.list === List.Nil) Maybe.Just(xs.value) else safeLast(xs.list)
	}
}

fun <T> last(xs : List<T>) : T {
	return when (val m = safeLast(xs)) {
		Maybe.Nothing -> throw IndexOutOfBoundsException()
		is Maybe.Just -> m.value
	}
}

fun <T> safeTail(xs : List<T>) : Maybe<List<T>> {
	return when (xs) {
		List.Nil -> Maybe.Nothing
		is List.Cons -> Maybe.Just(xs.list)
	}
}

fun <T> tail(xs : List<T>) : List<T> {
	return when (val m = safeTail(xs)) {
		Maybe.Nothing -> throw IndexOutOfBoundsException()
		is Maybe.Just -> m.value
	}
}

fun <T> safeInit(xs : List<T>) : Maybe<List<T>> {
	return when (xs) {
		List.Nil -> Maybe.Nothing
		is List.Cons -> safeInit(xs.list).let { list ->
			if (list !is Maybe.Just) Maybe.Just(List.Nil) else
				Maybe.Just(List.Cons(xs.value, list.value))
		}
	}
}

fun <T> init(xs : List<T>) : List<T> {
	return when (val m = safeInit(xs)) {
		Maybe.Nothing -> throw IndexOutOfBoundsException()
		is Maybe.Just -> m.value
	}
}

fun <T> take(len : Int, xs : List<T>) : List<T> {
	if (len < 0) IndexOutOfBoundsException(len.toString())

	return when (xs) {
		List.Nil -> List.Nil

		is List.Cons -> when (len) {
			0 -> List.Nil

			else -> List.Cons(xs.value, take(len - 1, xs.list))
		}
	}
}

fun <T> drop(len : Int, xs : List<T>) : List<T> {
	if (len < 0) IndexOutOfBoundsException(len.toString())

	return when (xs) {
		List.Nil -> List.Nil

		is List.Cons -> when (len) {
			0 -> xs

			else -> drop(len - 1, xs.list)
		}
	}
}

fun <T> length(xs : List<T>) : Int {
//	return when (xs) {
//		List.Nil -> 0
//		is List.Cons -> 1 + length(xs.list)
//	}

	var length = 0
	var list = xs

	while (list is List.Cons) {
		++ length
		list = list.list
	}

	return length
}

fun <A, B> foldr(init : B, list : List<A>, f : (A, B) -> B) : B {
	return when (list) {
		List.Nil -> init
		is List.Cons -> f(list.value, foldr(init, list.list, f))
	}
}

fun <A, B> foldl(init : B, list : List<A>, f : (B, A) -> B) : B {
	return when (list) {
		List.Nil -> init
		is List.Cons -> f(foldl(init, list.list, f), list.value)
	}
}

fun <A> fold(init : A, xs : List<A>, f : (A, A) -> A) : A {
	var value = init
	var list = xs

	while (list is List.Cons) {
		value = f(value, list.value)
		list = list.list
	}

	return value
}
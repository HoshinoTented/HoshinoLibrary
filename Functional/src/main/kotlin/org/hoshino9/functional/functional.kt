@file:Suppress("unused")

package org.hoshino9.functional

fun <A, B, C> flip(f : (A) -> (B) -> C) : (B) -> (A) -> C {
	return { b -> { a -> f(a)(b) } }
}

fun <A, B, C> flip(f: (A, B) -> C) : (B, A) -> C {
	return { b, a ->
		f(a, b)
	}
}
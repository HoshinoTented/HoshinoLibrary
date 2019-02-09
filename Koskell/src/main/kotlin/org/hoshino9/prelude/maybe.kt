package org.hoshino9.prelude

inline fun <A, B> maybe(maybe : Maybe<A>, default : B, trans : (A) -> B) : B {
	return when (maybe) {
		Maybe.Nothing -> default
		is Maybe.Just -> trans(maybe.value)
	}
}

fun <A> maybe(maybe : Maybe<A>, default : A) : A {
	return maybe(maybe, default, ::id)
}
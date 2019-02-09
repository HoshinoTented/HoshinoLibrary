package org.hoshino9.prelude

import org.junit.Test
import kotlin.test.assertEquals
import org.hoshino9.prelude.List.Cons
import org.hoshino9.prelude.List.Nil

class ListTest {
	private val xs = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))

	@Test
	fun generate() {
		assertEquals(xs, (1..5).toList())
	}

	@Test
	fun accessing() {
		assertEquals(1, head(xs))
		assertEquals(5, last(xs))

		assertEquals(Cons(2, Cons(3, Cons(4, Cons(5, Nil)))), tail(xs))
		assertEquals(Cons(1, Cons(2, Cons(3, Cons(4, Nil)))), init(xs))

		assertEquals(5, length(xs))
	}

	@Test
	fun folding() {
		assertEquals(5050, foldr(0, (1..100).toList(), Int::plus))
	}
}
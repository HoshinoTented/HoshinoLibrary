package org.hoshino9.prelude

sealed class List<out T> : Iterable<T> {
	private class Iterator<T>(var xs : List<T>) : kotlin.collections.Iterator<T> {
		override fun hasNext() : Boolean {
			return xs !== Nil
		}

		override fun next() : T {
			return if (hasNext()) {
				(xs as Cons).let { xs ->
					this.xs = xs.list

					xs.value
				}
			} else throw NoSuchElementException()
		}
	}

	abstract val list : List<T>

	data class Cons<out T>(val value : T, override val list : List<T>) : List<T>()
	object Nil : List<Nothing>() {
		override val list : List<Nothing> = Nil
	}

	override fun iterator() : kotlin.collections.Iterator<T> {
		return Iterator(list)
	}

	override fun toString() : String {
		return joinToString(prefix = "[", postfix = "]") {
			it.toString()
		}
	}
}
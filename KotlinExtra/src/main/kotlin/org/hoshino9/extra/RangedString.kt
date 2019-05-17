package org.hoshino9.extra

import kotlin.IndexOutOfBoundsException

class RangedString
@JvmOverloads
constructor(val src : String, val range : IntRange = 0 until src.length) : CharSequence, Iterable<Char> {
	inner class It : CharIterator() {
		private val it = range.iterator()

		override fun hasNext() : Boolean {
			return it.hasNext()
		}

		override fun nextChar() : Char {
			return src[it.next()]
		}
	}

	private val Int.realIndex : Int get() {
		return plus(range.first)
	}

	override val length : Int get() = range.last - range.first + 1

	override fun iterator() : CharIterator {
		return It()
	}

	override operator fun get(index : Int) : Char {
		if (index.realIndex in range) {
			return src[index.realIndex]
		} else throw StringIndexOutOfBoundsException(index.toString())
	}

	override fun subSequence(startIndex : Int, endIndex : Int) : RangedString {
		if (startIndex.realIndex in range && endIndex.realIndex - 1 in range) {
			return RangedString(src, startIndex.realIndex until endIndex.realIndex)
		} else throw IndexOutOfBoundsException()
	}

	override fun toString() : String {
		return range.joinToString(separator = "") {
			src[it].toString()
		}
	}

	override fun equals(other : Any?) : Boolean {
		if (this === other) return true
		if (other !is RangedString) return false

		if (src != other.src) return false
		if (range != other.range) return false

		return true
	}

	override fun hashCode() : Int {
		var result = src.hashCode()
		result = 31 * result + range.hashCode()
		return result
	}
}

val String.asRangedString : RangedString get() {
	return RangedString(this)
}

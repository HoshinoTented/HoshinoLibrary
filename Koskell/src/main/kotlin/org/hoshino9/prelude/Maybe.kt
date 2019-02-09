package org.hoshino9.prelude

sealed class Maybe<out T> {
	object Nothing : Maybe<kotlin.Nothing>() {
		override fun toString() : String {
			return "Nothing"
		}
	}


	data class Just<out T>(val value : T) : Maybe<T>() {
		override fun toString() : String {
			return "(Just $value)"
		}
	}
}
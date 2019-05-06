@file:Suppress("unused")

package org.hoshino9.utils

/**
 * this function make...
 * ```
 * val foo = fun (it: String): String { return it }
 * "qwq".apply(foo.returnUnit())
 * // "qwq".apply(foo) will throw a compile error
 * ```
 */
fun <R, T> ((R) -> T).returnUnit(): ((R) -> Unit) {
	return {
		this(it)
	}
}
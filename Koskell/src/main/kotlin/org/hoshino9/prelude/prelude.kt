@file:Suppress("unused")

package org.hoshino9.prelude

fun <A> id(a : A) : A = a

fun <A, B> const(a : A) : (B) -> A = { _ -> a }
fun <A, B> const(a : A, b : B) : A = a
@file:Suppress("unused")

package org.hoshino9.utils

fun Boolean.toInt() = if (this) 1 else 0
fun Number.toBoolean() = toInt() == 0

/**
 * this 随机到 end(不包含)
 */
infix fun Long.randomTo(end : Long) = this + (Math.random() * (end - this)).toLong()

fun IntRange.random() = first.toLong() randomTo last.toLong()
fun LongRange.random() = first randomTo last

fun Number.toBigInt() = toString().run(::BigInt)
fun Number.toBigDec() = toString().run(::BigDec)
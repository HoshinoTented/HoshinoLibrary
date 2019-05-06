@file:Suppress("unused")

package org.hoshino9.utils

import java.math.BigDecimal
import java.math.BigInteger

fun Number.toBigInt() = toString().run(::BigInteger)
fun Number.toBigDec() = toString().run(::BigDecimal)
@file:Suppress("unused")

package org.hoshino9.utils

import java.security.MessageDigest

val hexArray = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		'A', 'B', 'C', 'D', 'E', 'F')

fun byteArray2hex(byteArray : ByteArray) = CharArray(byteArray.size * 2).apply {
	var index = 0
	byteArray.forEach {
		this[index ++] = hexArray[it.toInt() ushr 4 and 0xf]
		this[index ++] = hexArray[it.toInt() and 0xf]
	}
}

fun md5ByteArray(input : String) : ByteArray = MessageDigest.getInstance("MD5").run {
	update(input.toByteArray())
	digest()
}

fun md5(input : String) : String = String(md5ByteArray(input).run(::byteArray2hex))

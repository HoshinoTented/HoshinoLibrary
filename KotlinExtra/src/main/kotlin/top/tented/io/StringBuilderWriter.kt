@file:Suppress("unused")

package top.tented.io

import java.io.Reader
import java.io.Writer

class StringBuilderWriter
@JvmOverloads constructor(private val builder : StringBuilder = StringBuilder()) : Writer() {
	val stringValue : String get() = builder.toString()

	override fun write(chars: CharArray?, off: Int, len: Int) {
		if (chars == null) throw NullPointerException("LaJi Java")
		builder.append(chars, off, len)
	}

	override fun flush() = Unit
	override fun close() = Unit
}
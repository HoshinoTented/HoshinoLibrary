@file:Suppress("unused")

package org.hoshino9.utils

import java.io.InputStream
import java.io.PrintStream
import java.util.stream.Collectors

fun printf(message : String, vararg args : Any?) : PrintStream = System.out.printf(message, * args)
fun printfErr(message : String, vararg args : Any?) : PrintStream = System.err.printf(message, * args)

fun printErr(message : Any?) = System.err.print(message)
fun printlnErr(message : Any?) = System.err.println(message)

inline fun forceRun(lambda : () -> Unit) {
	try {
		lambda()
	} catch (e : Exception) {
		//NOP
	}
}

inline fun catchRun(lambda : () -> Unit) {
	try {
		lambda()
	} catch (e : Exception) {
		e.printStackTrace()
	}
}
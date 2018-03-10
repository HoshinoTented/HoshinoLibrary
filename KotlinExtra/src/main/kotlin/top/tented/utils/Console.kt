package top.tented.utils

fun printf( message : String , vararg args : Any? ) = System.out.printf(message, * args)

fun printErr(message : Any?) = System.err.print(message)
fun printlnErr(message : Any?) = System.err.println(message)
fun printfErr(message : String, vararg args : Any?) = System.err.printf(message, * args)
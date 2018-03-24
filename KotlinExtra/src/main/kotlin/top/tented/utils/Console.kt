package top.tented.utils

import java.io.InputStream
import java.util.stream.Collectors

fun printf( message : String , vararg args : Any? ) = System.out.printf(message, * args)

fun printErr(message : Any?) = System.err.print(message)
fun printlnErr(message : Any?) = System.err.println(message)
fun printfErr(message : String, vararg args : Any?) = System.err.printf(message, * args)

fun forceRun(lambda : () -> Unit) {
    try {
        lambda()
    } catch ( e : Exception ) {
        //NOP
    }
}

fun catchRun(lambda : () -> Unit) {
    try {
        lambda()
    } catch ( e : Exception ) {
        e.printStackTrace()
    }
}

fun shell(command : String) : Pair<List<String>, List<String>> {
    var process : Process? = null
    var stdout = emptyList<String>()
    var stderr = emptyList<String>()
    try {
        process = Runtime.getRuntime().exec(command).apply {
            fun collectLines(it : InputStream) : List<String> {
                val reader = it.bufferedReader()
                val ret = reader.lines().collect(Collectors.toList())
                forceRun(reader::close)
                return ret
            }

            waitFor()
            stdout = inputStream.use(::collectLines)
            stderr = errorStream.use(::collectLines).apply {
                forceRun(::destroy)
            }
        }
    } catch ( e : Throwable ) {
        process?.destroy()
    }

    return stdout to stderr
}
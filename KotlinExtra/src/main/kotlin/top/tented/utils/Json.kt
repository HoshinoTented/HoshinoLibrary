package top.tented.utils

class JsonObject {
    val pairs = HashMap<String, Any?>()

    infix fun String.to(value : Any?) {
        pairs[this] = value
    }

    override fun toString() = buildString {
        append("{")
        pairs.forEach { key, value ->
            """
            |"$key":${if(value is CharSequence) "\"$value\"" else value},
            """.trimMargin().run(::append)
        }
    }.run { substring(0, length - 2) } + "}"
}

fun obj(body : JsonObject.() -> Unit) = JsonObject().apply(body)
fun json(body: JsonObject.() -> Unit) = obj(body)
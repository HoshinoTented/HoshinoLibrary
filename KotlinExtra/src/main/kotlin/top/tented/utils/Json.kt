package top.tented.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader
import java.io.StringReader

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
    }.run { substring(0, length - 1) } + "}"
}

fun obj(body : JsonObject.() -> Unit) = JsonObject().apply(body)
fun json(body: JsonObject.() -> Unit) = obj(body)

inline fun <reified T> Gson.fromJson(json : String) : T = fromJson(StringReader(json))
inline fun <reified T> Gson.fromJson(reader : Reader) : T = fromJson(reader, T::class.java)
inline fun <reified T> Gson.fromJsonByType(json : String) : T = fromJsonByType(StringReader(json))
inline fun <reified T> Gson.fromJsonByType(reader : Reader) : T = fromJson(
        reader,
        object : TypeToken<T>() {} .type
)
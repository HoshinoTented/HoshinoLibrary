package top.tented.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import java.io.Reader
import java.io.StringReader

class JsonObject : JSONObject() {
	inline fun obj(obj : JsonObject.() -> Unit) = json(obj)
	fun array(vararg values : Any?) = JSONArray(values.toList())
	infix fun String.to(other : Any?) = put(this, other)
}

inline fun json(obj : JsonObject.() -> Unit) = JsonObject().apply(obj)

inline fun <reified T> Gson.fromJson(json : String) : T = fromJson(StringReader(json))
inline fun <reified T> Gson.fromJson(reader : Reader) : T = fromJson(reader, T::class.java)
inline fun <reified T> Gson.fromJsonByType(json : String) : T = fromJsonByType(StringReader(json))
inline fun <reified T> Gson.fromJsonByType(reader : Reader) : T = fromJson(
	reader,
	object : TypeToken<T>() {}.type
)
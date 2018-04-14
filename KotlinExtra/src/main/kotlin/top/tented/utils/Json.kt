@file:Suppress("unused")

package top.tented.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import java.io.Reader
import java.io.StringReader

typealias GsonObject = com.google.gson.JsonObject

class JsonObject : JSONObject() {
	inline fun obj(obj : JsonObject.() -> Unit) = json(obj)
	fun array(vararg values : Any?) = JSONArray(values.toList())
	infix fun String.to(other : Any?) {
		put(this, other)
	}
}

inline fun json(obj : JsonObject.() -> Unit) = JsonObject().apply(obj)

inline fun <reified T> Gson.fromJson(json : String) : T = fromJson(StringReader(json))
inline fun <reified T> Gson.fromJson(reader : Reader) : T = fromJson(reader, T::class.java)

inline fun <reified T> Gson.fromJsonByType(json : String) : T = fromJsonByType(StringReader(json))
inline fun <reified T> Gson.fromJsonByType(reader : Reader) : T = fromJson(
		reader,
		object : TypeToken<T>() {}.type
)

inline fun <reified T> GsonBuilder.registerTypeAdapter(adapter : Any) = registerTypeAdapter(T::class.java, adapter) ?: this
inline fun <reified T> GsonBuilder.registerTypeHierarchyAdapter(adapter : Any) = registerTypeHierarchyAdapter(T::class.java, adapter) ?: this


operator fun GsonObject.set(key : String, element : JsonElement) = add(key, element)
operator fun GsonObject.set(key : String, value : String) = addProperty(key, value)
operator fun GsonObject.set(key : String, value : Number) = addProperty(key, value)
operator fun GsonObject.set(key : String, value : Boolean) = addProperty(key, value)
operator fun GsonObject.set(key : String, value : Char) = addProperty(key, value)
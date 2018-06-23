@file:Suppress("unused")

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.io.Reader
import java.io.StringReader

inline fun <reified T> Gson.fromJson(json : String) : T = fromJson(StringReader(json))
inline fun <reified T> Gson.fromJson(reader : Reader) : T = fromJson(reader, T::class.java)

inline fun <reified T> Gson.fromJsonByType(json : String) : T = fromJsonByType(StringReader(json))
inline fun <reified T> Gson.fromJsonByType(reader : Reader) : T = fromJson(
	reader,
	object : TypeToken<T>() {}.type
)

inline fun <reified T> GsonBuilder.registerTypeAdapter(adapter : Any) = registerTypeAdapter(T::class.java, adapter) ?: this
inline fun <reified T> GsonBuilder.registerTypeHierarchyAdapter(adapter : Any) = registerTypeHierarchyAdapter(T::class.java, adapter) ?: this


operator fun JsonObject.set(key : String, element : JsonElement) = add(key, element)
operator fun JsonObject.set(key : String, value : String) = addProperty(key, value)
operator fun JsonObject.set(key : String, value : Number) = addProperty(key, value)
operator fun JsonObject.set(key : String, value : Boolean) = addProperty(key, value)
operator fun JsonObject.set(key : String, value : Char) = addProperty(key, value)
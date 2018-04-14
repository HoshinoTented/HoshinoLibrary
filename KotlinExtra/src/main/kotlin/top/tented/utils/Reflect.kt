@file:Suppress("unused")

package top.tented.utils

import java.util.jar.JarFile
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
class FieldDelegate<T>(private val obj : Any?) {
	operator fun getValue(_this : Any?, property : KProperty<*>) : T? = obj?.javaClass?.getDeclaredField(property.name)?.run {
		isAccessible = true
		get(obj)
	} as? T

	operator fun setValue(_this : Any?, property : KProperty<*>, value : T?) = obj?.javaClass?.getDeclaredField(property.name)?.run {
		isAccessible = true
		set(obj, value)
	} as? T
}

@Throws(NoSuchMethodException::class)
inline fun <reified T : Any> T.function(name : String, vararg args : Any) : Any? {
	T::class.java.methods.forEach method@{
		if(it.name == name) {
			val parameters = it.parameterTypes
			if (parameters.size == args.size) {
				(0 until parameters.size).forEach checking@ {
					if (!parameters[it].isAssignableFrom(args[it]::class.java)) return@method
				}

				return it(this, * args)
			}
		}
	}

	throw NoSuchMethodException(
			"Not found method: $name${args.joinToString(prefix = "(", postfix = ")") { it::class.java.name }}"
	)
}

@Throws(NoSuchFieldException::class)
inline fun <reified T : Any?> T.field(name : String) : Any? =
		T::class.java.getDeclaredField(name).run {
			this.isAccessible = true
			get(this@field)
		}

//Get classes from jar file
fun Package.classes(jar : JarFile) =
		ArrayList<Class<*>>().apply {
			name.replace('.', '/').let { packageName ->
				jar.entries().forEach { entry ->
					entry.takeIf { it.name.startsWith(packageName) && it.name.endsWith(".class") }?.let {
						add(Class.forName(it.name.replace('/', '.')[0, -6]))
					}
				}
			}
		}.toTypedArray()
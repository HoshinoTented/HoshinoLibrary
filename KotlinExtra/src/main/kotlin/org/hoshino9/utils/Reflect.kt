@file:Suppress("unused")

package org.hoshino9.utils

import java.util.jar.JarFile
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

@Throws(NoSuchMethodException::class)
inline fun <reified T : Any> T.function(name : String, vararg args : Any) : Any? {
	T::class.java.methods.forEach method@{
		if (it.name == name) {
			val parameters = it.parameterTypes
			if (parameters.size == args.size) {
				repeat(parameters.size) checking@{
					if (! parameters[it].isAssignableFrom(args[it]::class.java)) return@method
				}

				return it(this, * args)
			}
		}
	}

	throw NoSuchMethodException(
			"$name${args.joinToString(prefix = "(", postfix = ")") { it::class.java.name }}"
	)
}

@Throws(NoSuchFieldException::class)
inline fun <reified T : Any?> T.field(name : String) : Any? =
		T::class.java.getDeclaredField(name).run {
			this.isAccessible = true
			get(this@field)
		}

//Get classes from jar file
fun Package.classes(jar : JarFile) = ArrayList<Class<*>>().apply {
	name.replace('.', '/').let { packageName ->
		jar.entries().forEach { entry ->
			entry.takeIf { it.name.startsWith(packageName) && it.name.endsWith(".class") }?.let {
				add(Class.forName(it.name.replace('/', '.')[0, - 6]))
			}
		}
	}
}.toTypedArray()
@file:Suppress("UNCHECKED_CAST")

package top.tented.file

import java.io.*

fun File.remove() : Boolean {
	if (isDirectory) {
		val files = listFiles()

		files.forEach {
			if (it.isDirectory) remove() else it.delete()
		}
	}

	return delete()
}

inline fun <reified T> InputStream.readObject() : T? = ObjectInputStream(this).use(ObjectInputStream::readObject) as? T

fun OutputStream.writeObject(obj : Serializable) {
	ObjectOutputStream(this).use {
		it.writeObject(obj)
	}
}


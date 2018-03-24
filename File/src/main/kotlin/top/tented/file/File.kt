@file:Suppress("UNCHECKED_CAST")

package top.tented.file

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

/**
 * File
 * @author Hoshino Tented
 * @date 2018/1/29 6:41
 */

fun File.writeObject(obj : Any) {
	if (! exists()) {
		parentFile.mkdirs()
		createNewFile()
	}

	ObjectOutputStream(FileOutputStream(this)).use { it.writeObject(obj) }
}

fun <T> File.readObject() : T? =
	takeIf { exists() }?.run { ObjectInputStream(FileInputStream(this)).use(ObjectInputStream::readObject) as? T }

fun File.remove() : Boolean {
	if (isDirectory) {
		val files = listFiles()

		files.forEach {
			if (it.isDirectory) remove() else it.delete()
		}
	}

	return delete()
}

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

fun File.writeObject(obj : Any)
{
    if (! exists())
    {
        parentFile.mkdirs()
        createNewFile()
    }

    ObjectOutputStream(FileOutputStream(this)).writeObject(obj)
}

fun File.readObject() : Any? =
        if( ! exists() ) null
        else ObjectInputStream(FileInputStream(this)).readObject()

fun File.remove() : Boolean
{
    if( isDirectory )
    {
        val files = listFiles()

        files.forEach {
            if( it.isDirectory ) remove() else it.delete()
        }
    }

    return delete()
}

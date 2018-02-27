package top.tented.file

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

/**
 * Config
 * @author Hoshino Tented
 * @date 2018/1/29 7:10
 */

class Config {
    companion object

    val file : File

    constructor(fileName : String) {
        this.file = File(fileName)
    }

    constructor(file : File) {
        this.file = file
    }

    /**
     * 返回本配置文件的所有键构成的Set
     */
    fun keySet() =
            file.takeIf { it.exists() }?.let {
                Properties().run {
                    load(FileInputStream(it))
                    keys
                }
            } ?: emptySet<Any>()

    /**
     * 移除某个键
     * @param key 键名
     */
    fun remove(key : String) =
            file.takeIf { it.exists() }?.let {
                Properties().run {
                    load(FileInputStream(it))
                    takeIf { it.containsKey(key) }?.run {
                        remove(key)
                        store(FileOutputStream(it), null)
                    }
                }
            } ?: false

    /**
     * 读取配置文件中的某个键
     * @param key 被读取的键
     */
    operator fun get(key : String) =
            file.takeIf { it.exists() }?.let {
                Properties().run {
                    load(FileInputStream(it))
                    this.getProperty(key)
                }
            }

    /**
     * 向配置文件中写入
     * @param key 键
     * @param value 值
     */
    operator fun set(key : String, value : Any?) =
            file.let {
                it.takeIf { it.exists() }?.run {
                    parentFile.mkdirs()
                    createNewFile()
                }

                Properties().run {
                    load(FileInputStream(it))
                    setProperty(key, value.toString())
                    store(FileOutputStream(it), null)
                }
            }
}
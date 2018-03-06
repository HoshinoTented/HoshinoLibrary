package top.tented.file

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.*

/**
 * Config
 * @author Hoshino Tented
 * @date 2018/1/29 7:10
 */

class Config(val file : File) {
    companion object {
        operator fun invoke(fileName : String) = Config(File(fileName))
    }

    val properties = Properties()
    val input by lazy { FileInputStream(file) }
    val output by lazy { FileOutputStream(file) }

    init {
        file.takeIf { it.isDirectory }?.run {
            throw IllegalArgumentException("Config file must not be a directory")
        }
    }

    /**
     * 返回本配置文件的所有键构成的Set
     */
    val keySet
        get() = file.takeIf { it.exists() }?.let {
            properties.run {
                load(input)
                keys
            }
        } ?: emptySet<Any>()

    /**
     * 移除某个键
     * @param key 键名
     */
    fun remove(key : String) =
            file.takeIf { it.exists() }?.let {
                properties.run {
                    load(input)
                    takeIf { it.containsKey(key) }?.run {
                        remove(key)
                        store(output, null)
                    }
                }
            } ?: false

    fun reset() {
        file.delete()
        file.parentFile.mkdirs()
        file.createNewFile()
    }

    /**
     * 读取配置文件中的某个键
     * @param key 被读取的键
     */
    operator fun get(key : String) =
            file.takeIf { it.exists() }?.let {
                properties.run {
                    load(input)
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
                it.takeIf { ! it.exists() }?.run {
                    parentFile.mkdirs()
                    createNewFile()
                }

                properties.run {
                    load(input)
                    setProperty(key, value.toString())
                    store(output, null)
                }
            }
}
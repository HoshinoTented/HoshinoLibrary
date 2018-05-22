@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package top.tented.internet

import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

/**
 * Request 网页请求类
 * @param url 将被请求的网页地址
 */
class Request(url : String) {
    companion object;

    private val header : HashMap<String, String> = HashMap()        //本请求的header

    val url : URL = URL(url)
    var application : String = "x-www-form-urlencoded"

    lateinit var cookie : Cookie             //进行doGet/doPost之后的cookie存放
    var charset : Charset = Charset.forName("UTF-8")        //doGet/doPost的编码, 默认UTF-8

    operator fun get(key : String) : String? = header[key]
    operator fun set(key : String, value : String) {
        header[key] = value
    }

    /**
     * doGet
     * 获取url的文本内容
     * @return 返回url的文本内容
     */
    fun doGet() : String {        //如果可以的话, 其实是打算用doPost来替代doGet的......但是我对这方面不是很了解, 不知道doGet和doPost有什么区别, 要是有的话就完蛋了...
        val connection : HttpURLConnection? = url.openConnection() as? HttpURLConnection

        if (connection != null) {
            connection.requestMethod = "GET"
            connection.setRequestProperty("Content-Type", "application/$application; charset=$charset")

            //do header save
            for ((key, value) in header) connection.setRequestProperty(key, value)

            connection.connect()

            val input = BufferedReader(InputStreamReader(connection.inputStream))
            val builder = StringBuilder("")
            val buffer = CharArray(1024)

            while (true) {
                val length : Int = input.read(buffer)

                if (length == -1) break else builder.append(String(buffer, 0, length))
            }

            input.close()

            this.cookie = Cookie(connection.getHeaderField("Set-Cookie"))       //存Cookie

            return builder.toString()
        } else throw Exception("一个不可能发生的错误发生了...大概是人品不好吧(connection was not instance of HttpURLConnection)")        //不需要捕获, 没事写的Exception而已, 下同
    }

    /**
     * doPost
     * 向url post 一段data
     * 如果data 为 null, 则仅post
     * @param data post的数据, 可空
     * @return 返回post结果
     */
    fun doPost(data : String? = null) : String {
        val connection : HttpURLConnection? = url.openConnection() as? HttpURLConnection

        if (connection != null) {
            connection.useCaches = false
            connection.instanceFollowRedirects = true
            connection.doOutput = true
            connection.doInput = true

            connection.requestMethod = "POST"

            connection.setRequestProperty("Content-Type", "application/$application; charset=$charset")

            //do header save
            for ((key, value) in header) connection.setRequestProperty(key, value)

            connection.connect()

            if (data != null) {
                val out = DataOutputStream(connection.outputStream)

                out.writeBytes(data)
                out.flush()
                out.close()
            }

            val input = BufferedReader(InputStreamReader(connection.inputStream))

            val builder = StringBuilder("")
            val buffer = CharArray(1024)

            while (true) {
                val length : Int = input.read(buffer)

                if (length == -1) break else builder.append(String(buffer, 0, length))
            }

            input.close()

            this.cookie = Cookie(connection.getHeaderField("Set-Cookie"))

            return builder.toString()
        } else throw Exception("一个不可能发生的错误发生了...大概是人品不好吧(connection was not instance of HttpURLConnection)")
    }

    /**
     * doDownLoad
     * 将url中的文件下载到path
     * @param path 文件的保存路径
     * @return 文件大小
     */
    fun doDownLoad(path : String) : Long {
        fun input(stream : InputStream) : Long {
            val file = File(path)

            if (!file.exists()) {
                file.parentFile.mkdirs()
                file.createNewFile()
            }

            val out = FileOutputStream(file)
            val buffer = ByteArray(1024)

            while (true) {
                val length = stream.read(buffer)

                if (length != -1) out.write(buffer, 0, length) else break
            }

            out.close()
            stream.close()

            return file.length()
        }

        val connection = url.openConnection() as? HttpURLConnection

        if (connection != null) return input(connection.inputStream)
        else throw Exception("一个不可能发生的错误发生了...大概是人品不好吧(connection was not instance of HttpURLConnection)")
    }
}
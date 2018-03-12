package top.tented.internet

class Cookie(private val cookie : MutableMap<String, String> = HashMap()) {
    companion object {
        operator fun invoke(cookie : String?) = Cookie(
                HashMap<String, String>().apply map@{
                    cookie?.split(';')?.forEach {
                        it.trim().split('=').apply list@{
                            if(size > 1) this@map[this[0]] = this[1]
                        }
                    }
                }
        )

        @JvmStatic
        fun newInstance(cookie : String) : Cookie = Cookie(cookie)
    }

    val size get() = cookie.size

    operator fun get(key : String) : String? = cookie[key]
    operator fun set(key : String, value : String) = cookie.set(key, value)

    override fun toString() = StringBuilder().apply {
        cookie.forEach { key, value ->
            append("$key=$value; ")
        }
    }.toString()
}
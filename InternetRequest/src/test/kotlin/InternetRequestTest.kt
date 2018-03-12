import org.junit.Test
import top.tented.internet.Request

class InternetRequestTest {
    @Test
    fun cookieTest() {
        println(Request("https://www.bilibili.com").doGet())
    }
}


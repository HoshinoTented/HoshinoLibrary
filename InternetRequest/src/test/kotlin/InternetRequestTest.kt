import org.junit.Test
import org.hoshino9.internet.Request

class InternetRequestTest {
    @Test
    fun cookieTest() {
        println(Request("https://www.bilibili.com").doGet())
    }
}


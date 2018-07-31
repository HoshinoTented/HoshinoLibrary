import org.hoshino9.utils.toBoolean
import org.junit.Test
import kotlin.test.assertEquals

class BooleanTest {
	@Test
	fun toBooleanTest() {
		assertEquals(false, 0.toBoolean())
		assertEquals(true, 1.toBoolean())
		assertEquals(true, true.toBoolean())
		assertEquals(true, "false".toBoolean())
		assertEquals(false, null.toBoolean())
	}
}
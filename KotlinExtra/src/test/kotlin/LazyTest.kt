import org.hoshino9.utils.RefreshableLazyImpl
import org.junit.Test
import kotlin.test.assertEquals

class LazyTest {
	private var isLowerCase = false
	private val delegate = RefreshableLazyImpl {
		if (isLowerCase) it.name.toLowerCase() else it.name.toUpperCase()
	}

	private val string by delegate

	@Test
	fun delegateTest() {
		assertEquals(string, "STRING")
		isLowerCase = true

		assertEquals(string, "STRING")
		delegate.refresh()

		assertEquals(string, "string")
	}
}
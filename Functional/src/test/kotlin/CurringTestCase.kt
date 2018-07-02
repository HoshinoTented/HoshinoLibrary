import org.hoshino9.functional.curring
import org.hoshino9.functional.uncurring
import org.junit.Test
import kotlin.test.assertEquals

class CurringTestCase {
	@Test
	fun curring() {
		val plus = curring<Int, Int, Int>(Int::plus)
		val next = plus(1)

		assertEquals(3, next(2))
	}

	@Test
	fun uncurring() {
		val plus = curring<Int, Int, Int>(Int::plus)
		val uncurringPlus = uncurring(plus)

		assertEquals(3, uncurringPlus(1, 2))
	}
}
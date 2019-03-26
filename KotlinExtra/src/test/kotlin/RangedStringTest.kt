import org.hoshino9.extra.RangedString
import org.junit.Test
import kotlin.test.assertEquals

class RangedStringTest {
	@Test
	fun iterate() {
		val str = "Hello, world!"
		val strIt = str.iterator()
		val rStrIt = RangedString(str).iterator()

		while (strIt.hasNext() /*&&*/ || rStrIt.hasNext()) {
			assertEquals(strIt.nextChar(), rStrIt.nextChar())
		}
	}

	@Test
	fun subSequence() {
		val range = 0 until 5
		val src = "Hello, world!"
		val a = RangedString(src)

		assertEquals(src.subSequence(range), a.subSequence(range).toString())
	}


}
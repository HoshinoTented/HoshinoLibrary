import org.junit.Test
import top.tented.utils.calendar
import top.tented.utils.year

class CalendarTest {

	@Test
	fun properties() {
		calendar.apply {
			year += 1
		}.run(::println)
	}
}
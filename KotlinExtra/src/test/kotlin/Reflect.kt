import org.junit.Test

class Reflect {
	@Test
	fun kfunction() {
		val a = {

		}

		val b = Reflect::kfunction
		println(b::class)
	}
}
import com.github.hoshinotented.newObject
import org.junit.Test
import org.python.core.PyFunction
import org.python.core.PySystemState
import org.python.util.PythonInterpreter

class PyObjectExtTest {
	val interpreter = kotlin.run {
		PySystemState.getBaseProperties().run {
			put("python.import.site", "false")
			PySystemState.initialize(this, this)
		}

		PythonInterpreter()
	}

	@Test
	fun newObj() {
		interpreter.newObject<PyFunction>("say", """
			def say(arg):
				print(arg)
		""".trimIndent())
	}
}
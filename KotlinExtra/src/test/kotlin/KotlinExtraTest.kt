import org.hoshino9.utils.*
import org.junit.Test
import java.util.Calendar
import java.util.jar.JarFile

class KotlinExtraTest {
	@Test
	fun classesFromPackage() {
		Package.getPackage("java.lang").classes(JarFile("/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/rt.jar")).forEach {
			println(it)
		}
	}

	private fun Package.classes(jar : JarFile) =
			ArrayList<Class<*>>().apply {
				name.replace('.', '/').let { packageName ->
					jar.entries().forEach { entry ->
						entry.takeIf { it.name.startsWith(packageName) && it.name.endsWith(".class") }?.let {
							add(Class.forName(it.name.replace('/', '.')[0, - 6]))
						}
					}
				}
			}.toTypedArray()

	@Test
	fun shell() {
		println(org.hoshino9.utils.shell("echo Hello world!"))
	}

	/**
	 * 用来测试 对于扩展属性的委托
	 */
	@Test
	fun calendarDelegate() {
		println(Calendar.getInstance().year)
	}

	@Test
	fun stringInsert() {
		val str = "1234567890"
		println(str.insert(3, "123"))
	}

	@Test
	fun div() {
		val str = "1234567890"
		println(str / 3)
	}

	@Test
	fun shortDiv() {
		10.shortDiv(2).run(::println)
	}

	@Test
	fun md5() {
		println(org.hoshino9.utils.md5("Hello world!"))
	}
}
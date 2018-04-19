import com.google.gson.Gson
import org.intellij.lang.annotations.Language
import org.junit.Test
import top.tented.utils.*
import java.io.File
import java.util.Calendar
import java.util.jar.JarFile

class KotlinExtraTest {
	@Test
	fun classesFromPackage() {
		Package.getPackage("java.lang").classes(JarFile("/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/rt.jar")).forEach {
			println(it)
		}
	}

	fun Package.classes(jar : JarFile) =
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
	fun reflectDelegate() {
		val value : CharArray? by FieldDelegate("132")

		value?.forEach {
			println(it)
		}
	}

	@Test
	fun jsonTest() {
		json {
			"my" to obj {
				"name" to obj {
					"first" to "Hoshino"
					"last" to "Tented"
				}

				"age" to 14

				"things" to array(1, 2, 3)
			}
		}.getJSONObject("my").get("things")::class.java.apply(::println)

		@Language("JSON") val json =
				"""{"my":{"name":{"last":"Tented", "first":"Hoshino"}, "things":[1, 2, 3], "age":14}}"""
	}

	@Test
	fun gsonTest() {
		Gson().let { gson ->
			val list = listOf("1", "2", "3")
			val file = File("/home/hoshino/Projects/IntelliJ/Kotlin/MainLibrary/KotlinExtra/src/test/resources/listJson.json")

			gson.toJson(list).let {
				file.writeText(it)
			}

			println(gson.fromJsonByType<List<String>>(file.readText()))
		}
	}

	@Test
	fun shell() {
		println(top.tented.utils.shell("echo Hello world!"))
	}

	class RangeClass(val a : Int, val b : Long, val c : LongRange)

	@Test
	fun closedRangeGson() {
		val gson = Gson()
		val closedRange = RangeClass(1, 2, 3L..4L)
		val json = gson.toJson(closedRange).apply(::println)
		gson.fromJson<RangeClass>(json).run {
			println("a: $a")
			println("b: $b")
			println("c: $c")
		}
	}

	@Test
	fun sourceTest() {
		val gson = Gson()
		gson.toJson(1 to 2)
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
}
import org.junit.Test
import top.tented.file.Config
import top.tented.file.readObject
import top.tented.file.writeObject
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Properties

class FileTest {
	val file = File("/home/hoshino/Projects/IntelliJ/Kotlin/MainLibrary/File/data/test.properties")
	val input by lazy { FileInputStream(file) }
	val output by lazy { FileOutputStream(file) }

	/**
	 * 看这个名字都应该知道是干啥的了
	 * 用来测试 load 和 save 使用同一个流对象是否可靠
	 */
	@Test
	fun loadOrSaveWithSameStream() {
		val start = System.currentTimeMillis()
		(0 until 1000).forEach {
			Config("/home/hoshino/Projects/IntelliJ/Kotlin/MainLibrary/File/data/$it.properties").run {
				set("key", "value")
				get("key")
			}
		}

		println(System.currentTimeMillis() - start)
	}

	/**
	 * 单个Properties对象能否重复load(stupid question
	 */
	@Test
	fun repeatLoad() {
		Properties().apply {
			load(input)
			load(input)
		}
	}

	@Test
	fun repeatSave() {
		Config(File("./src/test/resources/repeatSave.txt")).run {
			repeat(100) {
				this["a"] = it
			}
		}
	}

	@Test
	fun changeSet() {
		Config(file).run {
			reset()
			properties["1"] = "2"
			properties.store(output, null)
		}
	}

	@Test
	fun obj() {
		val file = File("./src/test/resources/obj.jobj")
		if (! file.exists()) {
			file.createNewFile()
		}

		FileOutputStream(file).writeObject(StringBuilder("123"))
		println(file.readText())
	}
}
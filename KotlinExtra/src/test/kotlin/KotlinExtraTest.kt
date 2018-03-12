import com.google.gson.Gson
import org.intellij.lang.annotations.Language
import org.junit.Test
import top.tented.utils.*
import java.io.File
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
                            add(Class.forName(it.name.replace('/', '.')[0, -6]))
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

                "things" to arrayListOf(1, 2, 3)
            }
        }.run(::println)

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
}
import org.junit.Test
import top.tented.utils.forEach
import java.util.jar.JarFile
import top.tented.utils.get

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
}
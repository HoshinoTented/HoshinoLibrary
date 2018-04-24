package top.tented.utils.generators

import org.intellij.lang.annotations.Language
import top.tented.utils.yesOrNo
import java.io.File

const val genPath = "src/main/gen"

/**
 * 短除 => 返回`商`和`余`
 */
internal object ShortDivGenerator {
	private const val output = "$genPath/top/tented/utils/_Math.kt"

	enum class Type {
		Byte,
		Short,
		Int,
		Long,
		Float,
		Double
	}

	private fun gen() {
		val outputFile = output.run(::File).apply {}
		if (outputFile.exists().not()) {
			outputFile.parentFile.mkdirs()
			outputFile.createNewFile()
		}

		buildString {
			Type.values().forEach { type ->
				Type.values().forEach { other ->
					val returnType = (type < other).yesOrNo(other, type).takeUnless { it == Type.Byte || it == Type.Short } ?: Type.Int
					@Language("Kotlin") val code = """fun $type.shortDiv(other : $other) : Pair<$returnType, $returnType> = div(other) to rem(other)"""
					appendln(code)
				}
			}
		}.let { outputFile.writeText(it) }
	}

	@JvmStatic
	fun main(args : Array<String>) = gen()
}
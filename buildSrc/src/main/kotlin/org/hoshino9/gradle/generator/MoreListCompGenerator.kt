package org.hoshino9.gradle.generator

open class MoreListCompGenerator : Generator() {
	private val limit = 17

	override val packageName: String = "extra"
	override val fileName: String = "list-component"

	override fun gen(): String {
		return buildString {
			repeat(limit) {
				val i = it + 6

				//language=kotlin
				appendln("""operator fun <T> List<T>.component$i(): T {
	return get(${i - 1})
}
				""")
			}
		}
	}
}
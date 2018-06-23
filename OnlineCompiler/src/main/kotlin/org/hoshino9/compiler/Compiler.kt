@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package org.hoshino9.compiler

import org.json.JSONObject
import org.hoshino9.compiler.exception.CompilationException
import org.hoshino9.internet.Request
import java.net.URLEncoder

class Compiler(val language : Language) {
	enum class Language(val id : Int) {
		Python2(0),
		Ruby(1),
		PHP(3),
		NodeJs(4),
		Scala(5),
		Go(6),
		Cpp(7),
		Java(8),
		VB(9),
		CSharp(10),
		Bash(11),
		ObjectC(12),
		Perl(14),
		Python3(15),
		Swift(16),
		Lua(17),
		Pascal(18),
		Kotlin(19)
	}

	companion object {
		private val request = Request("https://m.runoob.com/api/compile.php")
	}

	@Throws(CompilationException::class)
	fun compile(code : String) : String {
		val encoded = URLEncoder.encode(code, "UTF-8")
		val params = "code=$encoded&language=${language.id}"

		val jsonObj = JSONObject(request.doPost(params))

		val errors = jsonObj["errors"].toString()
		val result = jsonObj["output"].toString()

		return if (errors.isEmpty()) result else throw CompilationException(errors)
	}
}
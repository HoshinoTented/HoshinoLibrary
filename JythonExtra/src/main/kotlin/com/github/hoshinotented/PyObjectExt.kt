package com.github.hoshinotented

import org.intellij.lang.annotations.Language
import org.python.core.PyObject
import org.python.util.PythonInterpreter

inline fun <reified T : PyObject> PythonInterpreter.newObject(name : String, @Language("Python") code : String) : T {
	exec(code)
	return get(name).apply {
		locals.__delitem__(name)
	} as T
}
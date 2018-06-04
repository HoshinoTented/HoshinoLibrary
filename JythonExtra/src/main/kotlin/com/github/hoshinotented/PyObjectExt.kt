package com.github.hoshinotented

import org.python.core.PyObject
import org.python.util.PythonInterpreter

inline fun <reified T : PyObject> PythonInterpreter.newObject(code : String, name : String) : T {
	exec(code)
	return get(name).apply {
		locals.__delitem__(name)
	} as T
}
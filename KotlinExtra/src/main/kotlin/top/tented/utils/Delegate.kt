package top.tented.utils

import kotlin.reflect.KProperty

/**
 * 赋值次数限制的一个委托
 * @param default 默认值
 * @param times 最多赋值次数, 初始化也会算一次的哦
 */
class TimesVariable<T>(default : T, private val times : Int) {
	private var backField = default
	private var count = 0

	operator fun getValue(self : Any, property : KProperty<*>) : T = backField
	operator fun setValue(self : Any, property : KProperty<*>, value : T) {
		if (count < times) {
			backField = value
			count ++
		} else throw IllegalArgumentException("out of times: max $times")
	}
}

/**
 * Nullable
 */
class NullableTimesVariable<T>(default : T?, private val times : Int) {
	private var backField = default
	private var count = 0

	operator fun getValue(self : Any, property : KProperty<*>) : T? = backField

	operator fun setValue(self : Any, property : KProperty<*>, value : T?) {
		if (count < times) {
			backField = value
			count ++
		} else throw IllegalArgumentException("out of times: max $times")
	}
}
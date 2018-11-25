package org.hoshino9.utils

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

interface RefreshableLazy<T> : Lazy<T> {
	fun refresh()
}

internal object UNINITIALIZED

//Unsafe!!
class RefreshableLazyImpl<T>(val init : (KProperty<*>) -> T) : RefreshableLazy<T>, ReadOnlyProperty<Any?, T> {
	private lateinit var property : KProperty<*>
	private var _value : Any? = UNINITIALIZED
	@Deprecated("Unsafe property")
	override val value : T
		get() {
			if (_value === UNINITIALIZED) {
				_value = init(property)
			}

			@Suppress("UNCHECKED_CAST")
			return _value as T
		}

	override fun getValue(thisRef : Any?, property : KProperty<*>) : T {
		this.property = property
		return value
	}

	override fun refresh() {
		if (_value !== UNINITIALIZED) this._value = init(property)
	}

	override fun isInitialized() : Boolean {
		return _value == UNINITIALIZED
	}
}
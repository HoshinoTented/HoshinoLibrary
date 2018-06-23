@file:Suppress("unused")

package org.hoshino9.utils

import java.util.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private class CalendarDelegate(val flag : Int) : ReadWriteProperty<Calendar, Int> {
	override operator fun getValue(thisRef : Calendar, property : KProperty<*>) : Int = thisRef.get(flag)
	override operator fun setValue(thisRef : Calendar, property : KProperty<*>, value : Int) = thisRef.set(flag, value)
}

var Calendar.year by CalendarDelegate(Calendar.YEAR)
var Calendar.month by CalendarDelegate(Calendar.MONTH)
var Calendar.day by CalendarDelegate(Calendar.DAY_OF_MONTH)
var Calendar.hour by CalendarDelegate(Calendar.HOUR_OF_DAY)
var Calendar.minute by CalendarDelegate(Calendar.MINUTE)
var Calendar.second by CalendarDelegate(Calendar.SECOND)

@get:JvmName("newCalendar") val calendar : Calendar get() = Calendar.getInstance()
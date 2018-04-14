@file:Suppress("unused")

package top.tented.utils

import java.util.*
import kotlin.reflect.KProperty

private class CalendarDelegate(val flag : Int) {
	operator fun getValue(self : Calendar, property : KProperty<*>) : Int = self.get(flag)
	operator fun setValue(self : Calendar, property : KProperty<*>, value : Int) = self.set(flag, value)
}

var Calendar.year by CalendarDelegate(Calendar.YEAR)
var Calendar.month by CalendarDelegate(Calendar.MONTH)
var Calendar.day by CalendarDelegate(Calendar.DAY_OF_MONTH)
var Calendar.hour by CalendarDelegate(Calendar.HOUR_OF_DAY)
var Calendar.minute by CalendarDelegate(Calendar.MINUTE)
var Calendar.second by CalendarDelegate(Calendar.SECOND)

val calendar get() = Calendar.getInstance() !!
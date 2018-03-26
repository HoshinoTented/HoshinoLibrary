package top.tented.utils

import java.util.*
import kotlin.reflect.KProperty

private class CalendarDelegate(val flag : Int) {
    operator fun getValue(_this : Any?, property : KProperty<*>) =
            _this.uncheckCast<Calendar>().get(flag)

    operator fun setValue(_this : Any?, property : KProperty<*>, value : Int) =
            _this.uncheckCast<Calendar>().set(flag, value)
}

var Calendar.year by CalendarDelegate(Calendar.YEAR)
val Calendar.month by CalendarDelegate(Calendar.MONTH)
val Calendar.day by CalendarDelegate(Calendar.DAY_OF_MONTH)
val Calendar.hour by CalendarDelegate(Calendar.HOUR_OF_DAY)
val Calendar.minute by CalendarDelegate(Calendar.MINUTE)
val Calendar.second by CalendarDelegate(Calendar.SECOND)

val calendar get() = Calendar.getInstance()
package com.koshake1.pulseapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

val noteTimeId : Long
    get() = GregorianCalendar().time.time

fun getDate(): String {
    val calendar = GregorianCalendar()
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH)
    val year = calendar.get(Calendar.YEAR)
    return String.format(Locale.getDefault(), "%02d.%02d.%04d", day, month + 1, year)
}

fun getTime(): String {
    val calendar = GregorianCalendar()
    val hour = calendar.get(Calendar.HOUR)
    val minute = calendar.get(Calendar.MINUTE)
    return String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
}
@Parcelize
data class Note(
    val id : Long = noteTimeId,
    val date : String = getDate(),
    val time : String = getTime(),
    val sys_pressure : String = "",
    val dias_pressure : String = "",
    val pulse : String = ""
) : Parcelable

package com.example.careerpathmate.local.typeconverters

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DateTypeConverter {
    private val formatter = SimpleDateFormat(TIMESTAMP_FORMAT, Locale.getDefault())
    @TypeConverter
    fun stringToCalender(date: String?): Calendar? {
        if (date == null) return null
        val cal = Calendar.getInstance()
        cal.time = formatter.parse(date) ?: cal.time
        return cal
    }

    @TypeConverter
    fun calenderToString(date: Calendar?): String? {
        if (date == null) return null
        return formatter.format(date.time)
    }

    companion object {
        const val TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    }
}
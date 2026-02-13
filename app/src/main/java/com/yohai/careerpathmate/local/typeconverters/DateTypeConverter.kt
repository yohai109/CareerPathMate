package com.yohai.careerpathmate.local.typeconverters

import androidx.room.TypeConverter
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DateTypeConverter {
    @TypeConverter
    fun stringToCalender(date: String?): Calendar? {
        if (date == null) return null
        val cal = Calendar.getInstance()
        try {
            val formatter = SimpleDateFormat(TIMESTAMP_FORMAT, Locale.getDefault())
            cal.time = formatter.parse(date) ?: cal.time
        } catch (e: Exception) {
            Timber.w(e, "Can't parse date: $date, using current time")
        }
        return cal
    }

    @TypeConverter
    fun calenderToString(date: Calendar?): String? {
        if (date == null) return null
        val formatter = SimpleDateFormat(TIMESTAMP_FORMAT, Locale.getDefault())
        return formatter.format(date.time)
    }

    companion object {
        const val TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    }
}
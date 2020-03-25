package com.jonasthuresson.onealarmclock.db

import androidx.room.TypeConverter
import java.time.LocalTime

class Converters {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalTime? {
        if(value == null) return  null
        val split = value.split(":")
        val hour = split[0].toInt()
        val minute = split[1].toInt()
        return LocalTime.of(hour, minute)
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalTime?): String? {
        return date?.hour.toString() + ":" + date?.minute.toString()
    }
}
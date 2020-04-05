package com.jonasthuresson.onealarmclock.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime

@Entity
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val time: LocalTime,
    val repeats: Int = 0,
    val snoozeDurationInSeconds: Int = 0
) {
    companion object{
        fun snoozeDurationMinutesToSeconds(minutes: Int) = minutes * 60
    }
    fun isRepeating() = repeats or 0
    fun setDay(day: DAYS, boolean: Boolean): Alarm =
        copy(repeats = (if (boolean) repeats or day.value else repeats and day.value.inv()))
    fun isDaySetToRepeat(day: DAYS) = repeats and day.value != 0
    fun setSnoozeDurationInSeconds(seconds: Int): Alarm = copy(snoozeDurationInSeconds = seconds)
    fun setSnoozeDurationInMinutes(minutes: Int): Alarm = setSnoozeDurationInSeconds(Alarm.snoozeDurationMinutesToSeconds(minutes))
    fun getSnoozeDurationInMinutes() = snoozeDurationInSeconds / 60
}

enum class DAYS(val value: Int) {
        MONDAY      (0b1000000),
        TUESDAY     (0b0100000),
        WEDNESDAY   (0b0010000),
        THURSDAY    (0b0001000),
        FRIDAY      (0b0000100),
        SATURDAY    (0b0000010),
        SUNDAY      (0b0000001)
}

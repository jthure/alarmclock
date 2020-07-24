package com.jonasthuresson.onealarmclock.model

import com.jonasthuresson.onealarmclock.db.DbAlarm
import java.time.LocalTime

data class Alarm(
    val id: Long = 0,
    val time: LocalTime,
    val repeats: Int = 0,
    val snoozeDurationInSeconds: Int = 0,
    val sound: AlarmSound = AlarmSound("")
) {
    companion object {
        fun snoozeDurationMinutesToSeconds(minutes: Int) = minutes * 60
    }

    constructor(dbAlarm: com.jonasthuresson.onealarmclock.db.Alarm) : this(
        dbAlarm.id,
        dbAlarm.time,
        dbAlarm.repeats,
        dbAlarm.snoozeDurationInSeconds
    )

    fun isRepeating() = repeats or 0
    fun setDay(day: DAYS, boolean: Boolean): Alarm =
        copy(repeats = (if (boolean) repeats or day.value else repeats and day.value.inv()))

    fun isDaySetToRepeat(day: DAYS) = repeats and day.value != 0
    fun setSnoozeDurationInSeconds(seconds: Int): Alarm = copy(snoozeDurationInSeconds = seconds)
    fun setSnoozeDurationInMinutes(minutes: Int): Alarm =
        setSnoozeDurationInSeconds(snoozeDurationMinutesToSeconds(minutes))

    fun getSnoozeDurationInMinutes() = snoozeDurationInSeconds / 60

    fun setSound(sound: AlarmSound) = copy(sound = sound)

    fun toDbAlarm() = DbAlarm(id, time, repeats, snoozeDurationInSeconds)
    enum class DAYS(val value: Int) {
        MONDAY(0b1000000),
        TUESDAY(0b0100000),
        WEDNESDAY(0b0010000),
        THURSDAY(0b0001000),
        FRIDAY(0b0000100),
        SATURDAY(0b0000010),
        SUNDAY(0b0000001)
    }
}


package com.jonasthuresson.onealarmclock.data

import com.jonasthuresson.onealarmclock.db.AlarmDao
import com.jonasthuresson.onealarmclock.model.Alarm
import javax.inject.Inject


class AlarmsRepo @Inject constructor(private val alarmDao: AlarmDao) {

    suspend fun getAlarms(): List<Alarm> = alarmDao.getAll().map { Alarm(it) }
    suspend fun getAlarmById(id: Long): Alarm? =
        alarmDao.loadAllByIds(longArrayOf(id)).firstOrNull()?.let { Alarm(it) }

    suspend fun addAlarm(alarm: Alarm): Alarm = alarm.copy(id = alarmDao.insert(alarm.toDbAlarm()))

}

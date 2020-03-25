package com.jonasthuresson.onealarmclock.android.ui.alarms

import androidx.lifecycle.LiveData
import com.jonasthuresson.onealarmclock.android.OneAlarmApplication
import com.jonasthuresson.onealarmclock.db.Alarm
import com.jonasthuresson.onealarmclock.db.AlarmDao
import com.jonasthuresson.onealarmclock.db.AppDatabase
import com.jonasthuresson.onealarmclock.di.DaggerApplicationComponent
import dagger.android.DaggerApplication
import java.time.LocalTime
import javax.inject.Inject


class AlarmsRepo @Inject constructor(private val alarmDao: AlarmDao) {

    suspend fun getAlarms(): List<Alarm> = alarmDao.getAll()
    suspend fun getAlarmById(id: Long): Alarm? = alarmDao.loadAllByIds(longArrayOf(id)).firstOrNull()

    suspend fun addAlarm(alarm: Alarm): Alarm = alarm.copy(id = alarmDao.insert(alarm))

}

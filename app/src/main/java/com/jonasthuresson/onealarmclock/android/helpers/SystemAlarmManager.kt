package com.jonasthuresson.onealarmclock.android.helpers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.jonasthuresson.onealarmclock.MainActivity
import com.jonasthuresson.onealarmclock.db.Alarm
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*
import javax.inject.Inject

class SystemAlarmManager constructor(private val context: Context) {

    companion object{
        const val EXTRA_ALARM_ID = "com.jonasthuresson.onealarmclock.extra.alarmid"
    }

    private var alarmManager: AlarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun addAlarm(alarm: Alarm) {

        val c = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY, alarm.time.hour)
        c.set(Calendar.MINUTE, alarm.time.minute)
        c.set(Calendar.SECOND, alarm.time.second)

        val alarmInfoPendingIntent =
            PendingIntent.getActivity(context, alarm.id.toInt(), Intent(context, MainActivity::class.java), 0)

        val alarmInfo = AlarmManager.AlarmClockInfo(c.timeInMillis, alarmInfoPendingIntent)

        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        alarmIntent.putExtra(EXTRA_ALARM_ID, alarm.id)
        alarmManager.setAlarmClock(
            alarmInfo,
            PendingIntent.getBroadcast(context, alarm.id.toInt(), alarmIntent, 0)
        )
    }
}
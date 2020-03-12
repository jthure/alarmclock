package com.jonasthuresson.onealarmclock.android.helpers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.jonasthuresson.onealarmclock.MainActivity
import com.jonasthuresson.onealarmclock.model.alarms.Alarm

class SystemAlarmManager(private val context: Context) {
    private var alarmManager: AlarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun addAlarm(alarm: Alarm) {
        val alarmInfoPendingIntent =
            PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0)
        val alarmInfo = AlarmManager.AlarmClockInfo(0, alarmInfoPendingIntent)
        alarmManager?.setAlarmClock(
            alarmInfo,
            PendingIntent.getBroadcast(context, 0, Intent(context, AlarmReceiver::class.java), 0)
        )
    }
}
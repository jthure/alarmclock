package com.jonasthuresson.onealarmclock.android.helpers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.jonasthuresson.onealarmclock.android.services.BaseAlarmService
import com.jonasthuresson.onealarmclock.android.services.SpotifyAlarmService


class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val ALARM_NOTFICATION_CHANNEL =
            "com.jonasthuresson.onealarmclock.notificationchannels.alarm"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val id = intent?.getLongExtra(SystemAlarmManager.EXTRA_ALARM_ID, -1)
        Log.d("AlarmReceiver", "Alarm received. Alarm id: $id")
//        val i = Intent(MainActivity.ACTION_ALARM, null, context, MainActivity::class.java)
//        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        i.putExtra(SystemAlarmManager.EXTRA_ALARM_ID, id)
//
//        val pi = PendingIntent.getActivity(
//            context,
//            0, i,
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )
//
//        val builder = NotificationCompat.Builder(context!!, ALARM_NOTFICATION_CHANNEL)
//            .setSmallIcon(R.mipmap.ic_launcher)
//            .setContentTitle("Alarm triggered")
//            .setAutoCancel(true)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setFullScreenIntent(pi, true)
//
//
//        val mgr = context.getSystemService(NotificationManager::class.java)
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
//            && mgr.getNotificationChannel(ALARM_NOTFICATION_CHANNEL) == null
//        ) {
//            val channel = NotificationChannel(
//                ALARM_NOTFICATION_CHANNEL,
//                "Alarms",
//                NotificationManager.IMPORTANCE_HIGH
//            )
//            channel.setSound(null, null)
//
//            mgr.createNotificationChannel(
//                channel
//            )
//        }


        //mgr.notify(id!!.toInt(), builder.build())


        val serviceIntent: Intent =
            Intent(
                BaseAlarmService.ACTION_ALARM_START,
                null,
                context,
                SpotifyAlarmService::class.java
            )
        serviceIntent.putExtra(SystemAlarmManager.EXTRA_ALARM_ID, id)
        context?.startForegroundService(serviceIntent)
    }
}
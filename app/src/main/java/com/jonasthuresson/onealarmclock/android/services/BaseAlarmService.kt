package com.jonasthuresson.onealarmclock.android.services

import android.app.*
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.jonasthuresson.onealarmclock.R
import com.jonasthuresson.onealarmclock.TAG
import com.jonasthuresson.onealarmclock.android.MainActivity
import com.jonasthuresson.onealarmclock.android.helpers.AlarmReceiver
import com.jonasthuresson.onealarmclock.android.helpers.SystemAlarmManager
import com.jonasthuresson.onealarmclock.data.AlarmsRepo
import com.jonasthuresson.onealarmclock.model.Alarm
import dagger.android.AndroidInjection
import kotlinx.coroutines.launch
import javax.inject.Inject


abstract class BaseAlarmService : LifecycleService() {
    companion object {
        const val ACTION_ALARM_START = "com.jonasthuresson.onealarmclock.action.alarm_start"
        const val ACTION_ALARM_STOP = "com.jonasthuresson.onealarmclock.action.alarm_stop"
    }

    @Inject
    lateinit var alarmsRepo: AlarmsRepo

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
//        (application as OneAlarmApplication).getComponent()?.inject(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val id = intent?.getLongExtra(SystemAlarmManager.EXTRA_ALARM_ID, -1)
        if (id == null || id == -1L) {
            Log.d(TAG, "Invalid id: $id in intent. Stopping service...")
            stopSelf()
            return Service.START_NOT_STICKY
        }
        Log.d(TAG, "Alarm intent received. Alarm id: $id")
        lifecycleScope.launch {
            val alarm = alarmsRepo.getAlarmById(id)
            val notification = alarm?.let { buildNotification(it) }
            startForeground(alarm?.id?.toInt()!!, notification)
            handleAction(intent.action)
        }

        // service will not be recreated if abnormally terminated
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSound()
    }

    private fun handleAction(action: String?) {
        when (action) {
            ACTION_ALARM_START -> startSound()
            ACTION_ALARM_STOP -> stopSound()
        }
    }

    private fun buildNotification(alarm: Alarm): Notification {
        val i = Intent(MainActivity.ACTION_ALARM, null, this, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        i.putExtra(SystemAlarmManager.EXTRA_ALARM_ID, alarm.id)

        val pi = PendingIntent.getActivity(
            this,
            alarm.id.toInt(), i,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(this, AlarmReceiver.ALARM_NOTFICATION_CHANNEL)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Alarm")
            .setContentText("Alarm with id $alarm.id was triggered!")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setFullScreenIntent(pi, true)

        val mgr = this.getSystemService(NotificationManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
            && mgr?.getNotificationChannel(AlarmReceiver.ALARM_NOTFICATION_CHANNEL) == null
        ) {
            val channel = NotificationChannel(
                AlarmReceiver.ALARM_NOTFICATION_CHANNEL,
                "Alarms",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.setSound(null, null)

            mgr?.createNotificationChannel(
                channel
            )
        }
        return builder.build()
    }

    protected abstract fun startSound()
    protected abstract fun stopSound()
}
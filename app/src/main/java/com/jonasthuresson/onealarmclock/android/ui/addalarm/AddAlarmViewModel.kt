package com.jonasthuresson.onealarmclock.android.ui.addalarm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonasthuresson.onealarmclock.android.helpers.SystemAlarmManager
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsRepo
import com.jonasthuresson.onealarmclock.db.Alarm
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

class AddAlarmViewModel @Inject constructor(private val repo: AlarmsRepo, private val alarmManager: SystemAlarmManager): ViewModel() {
    var existingAlarm: Alarm = alarmNow()
    fun resetAlarm(){ existingAlarm = alarmNow() }

    fun saveAlarm(alarm: Alarm) {
        viewModelScope.launch {
            val insertedAlarm = repo.addAlarm(alarm)
            alarmManager.addAlarm(insertedAlarm)
        }
    }

    private fun alarmNow() = Alarm(time = LocalTime.now())
}


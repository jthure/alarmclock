package com.jonasthuresson.onealarmclock.android.ui.addalarm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonasthuresson.onealarmclock.android.helpers.SystemAlarmManager
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsRepo
import com.jonasthuresson.onealarmclock.db.Alarm
import com.jonasthuresson.onealarmclock.db.DAYS
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

class AddAlarmViewModel @Inject constructor(
    private val repo: AlarmsRepo,
    private val alarmManager: SystemAlarmManager
) : ViewModel() {
    private val _existingAlarm = MutableLiveData<Alarm>(alarmNow())
    val existingAlarm: LiveData<Alarm>
        get() = _existingAlarm

    fun resetAlarm() {
        _existingAlarm.value = alarmNow()
    }

    fun saveAlarm() {
        viewModelScope.launch {
            _existingAlarm.value?.let {
                val insertedAlarm = repo.addAlarm(it)
                alarmManager.addAlarm(insertedAlarm)
            }

        }
    }

    fun updateAlarm(updatedAlarm: Alarm?) {
        _existingAlarm.value = updatedAlarm
    }

    fun setAlarmTime(hour: Int, minute: Int) {
        _existingAlarm.value = _existingAlarm.value?.copy(
            time = LocalTime.of(hour, minute)
        )
    }

    fun setAlarmDay(days: DAYS, isRepeated: Boolean){
        _existingAlarm.value?.setDay(days, isRepeated)
    }

    fun increaseSnooze() {
        _existingAlarm.value?.let { alarm -> setSnooze(alarm.snoozeDurationInSeconds + 60) }
    }
    fun decreaseSnooze() {
        _existingAlarm.value?.let { alarm -> setSnooze(alarm.snoozeDurationInSeconds - 60) }
    }

    private fun setSnooze(seconds: Int) {
        _existingAlarm.value = _existingAlarm.value?.setSnoozeDurationInSeconds(seconds)
    }

    private fun alarmNow() = Alarm(time = LocalTime.now())
}


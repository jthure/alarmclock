package com.jonasthuresson.onealarmclock.android.ui.triggeredalarm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonasthuresson.onealarmclock.data.AlarmsRepo
import com.jonasthuresson.onealarmclock.model.Alarm
import kotlinx.coroutines.launch
import javax.inject.Inject

class TriggeredAlarmViewModel @Inject constructor(private val alarmsRepo: AlarmsRepo) :
    ViewModel() {
    private var _triggeredAlarm = MutableLiveData<Alarm>()
    val triggeredAlarm: LiveData<Alarm>
        get() = _triggeredAlarm

    fun fetchTriggeredAlarm(alarmId: Long) {
        viewModelScope.launch {
            val alarm = alarmsRepo.getAlarmById(alarmId)
            _triggeredAlarm.value = alarm
        }
    }

    fun snooze() {

    }
}

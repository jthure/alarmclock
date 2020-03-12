package com.jonasthuresson.onealarmclock.android.ui.alarms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jonasthuresson.onealarmclock.managers.SystemAlarmManager
import com.jonasthuresson.onealarmclock.model.alarms.Alarm
import java.time.LocalTime

class AlarmsViewModel : ViewModel() {
    private var repo: AlarmsRepo = AlarmsRepo()
    private val sysAlarmMgr: SystemAlarmManager = SystemAlarmManager()

    private var _alarms: MutableLiveData<Array<Alarm>> = MutableLiveData()
    val alarms: LiveData<Array<Alarm>>
        get() = _alarms

    fun fetchAlarms() {
        _alarms.value = repo.getAlarms()
    }

    fun saveAlarm(time: LocalTime) {
        repo.addAlarm(Alarm(time))

        fetchAlarms()
    }

}

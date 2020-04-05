package com.jonasthuresson.onealarmclock.android.ui.alarms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonasthuresson.onealarmclock.android.helpers.SpotifyManager
import com.jonasthuresson.onealarmclock.android.helpers.SystemAlarmManager
import com.jonasthuresson.onealarmclock.db.Alarm
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

class AlarmsViewModel @Inject constructor(
    private val repo: AlarmsRepo,
    private val alarmManager: SystemAlarmManager
//    val spotifyManagerLiveData: LiveData<SpotifyManager>
) : ViewModel() {
    //private var repo: AlarmsRepo = AlarmsRepo()

    private var _alarms: MutableLiveData<List<Alarm>> = MutableLiveData()
    val alarms: LiveData<List<Alarm>> = _alarms

    fun fetchAlarms() {
        viewModelScope.launch {
            _alarms.value = repo.getAlarms()
        }
    }

//    fun saveAlarm(alarm: Alarm) {
//        viewModelScope.launch {
//            val insertedAlarm = repo.addAlarm(alarm)
//            alarmManager.addAlarm(insertedAlarm)
//            fetchAlarms()
//        }
//    }

}

package com.jonasthuresson.onealarmclock.ui.alarms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jonasthuresson.onealarmclock.model.alarms.Alarm
import javax.inject.Inject

class AlarmsViewModel : ViewModel() {
    private var repo: AlarmsRepo = AlarmsRepo()

    private var _alarms: MutableLiveData<Array<Alarm>> = MutableLiveData()
    val alarms: LiveData<Array<Alarm>>
        get() = _alarms

    fun fetchAlarms() {
        _alarms.value = repo.getAlarms()
    }

}

package com.jonasthuresson.onealarmclock.ui.alarms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jonasthuresson.onealarmclock.model.alarms.Alarm

class AlarmsViewModel(private val repo: AlarmsRepo) : ViewModel() {
    private var _alarms: MutableLiveData<ArrayList<Alarm>> = MutableLiveData()
    val alarms: LiveData<ArrayList<Alarm>>
        get() = _alarms
}

package com.jonasthuresson.onealarmclock.android.ui.alarms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonasthuresson.onealarmclock.data.AlarmsRepo
import com.jonasthuresson.onealarmclock.model.Alarm
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlarmsViewModel @Inject constructor(
    private val repo: AlarmsRepo
) : ViewModel() {

    private var _alarms: MutableLiveData<List<Alarm>> = MutableLiveData()
    val alarms: LiveData<List<Alarm>> = _alarms

    fun fetchAlarms() {
        viewModelScope.launch {
            _alarms.value = repo.getAlarms()
        }
    }

}

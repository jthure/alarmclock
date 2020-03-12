package com.jonasthuresson.onealarmclock.android.ui.addalarm

import androidx.lifecycle.ViewModel
import java.time.LocalTime

class AddAlarmViewModel: ViewModel() {
    private var time: LocalTime = LocalTime.now()
    fun setTime(hour: Int, minute: Int){
        time = LocalTime.of(hour,minute)
    }

    fun getTime(): LocalTime = time
}
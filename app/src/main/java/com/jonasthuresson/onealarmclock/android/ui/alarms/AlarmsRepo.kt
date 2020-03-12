package com.jonasthuresson.onealarmclock.android.ui.alarms

import com.jonasthuresson.onealarmclock.model.alarms.Alarm


class AlarmsRepo () {
    private var alarms: Array<Alarm> = emptyArray()

    fun getAlarms() : Array<Alarm>{
        return alarms
    }
    fun addAlarm(alarm: Alarm){
        alarms += alarm
    }
}

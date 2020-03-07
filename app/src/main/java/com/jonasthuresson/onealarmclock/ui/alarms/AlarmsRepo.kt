package com.jonasthuresson.onealarmclock.ui.alarms

import com.jonasthuresson.onealarmclock.model.alarms.Alarm
import dagger.Component
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmsRepo @Inject constructor() {
    fun getAlarms() : Array<Alarm>{
        return arrayOf(Alarm(LocalDateTime.now().plusHours(1)), Alarm(LocalDateTime.now().plusDays(2)))
    }
}

@Component
interface AlarmsRepoFactory {
    fun alarmsRepo()
}
package com.jonasthuresson.onealarmclock.model.alarms

import java.time.LocalDateTime
import java.time.LocalTime

class Alarm(val time: LocalTime, val tune: Tune) {

    constructor(time: LocalTime) : this(time, Tune())
}
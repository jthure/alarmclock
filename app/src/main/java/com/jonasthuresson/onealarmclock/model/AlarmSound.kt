package com.jonasthuresson.onealarmclock.model

abstract class AlarmSound(protected val title: String, protected val uri: String) {

    override fun toString(): String = title
}
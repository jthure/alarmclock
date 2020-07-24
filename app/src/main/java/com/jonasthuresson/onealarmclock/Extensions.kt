package com.jonasthuresson.onealarmclock

fun String.toActivityResultCode() = hashCode().and(0xF0)

val Any.TAG: String get() = javaClass.simpleName

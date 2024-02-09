package com.adnan.cmpclock.util

import kotlinx.datetime.LocalTime

fun LocalTime.toAnalog(): String {
    val buf = StringBuilder(8)
    val hourValue = hour
    val minuteValue = minute
    val secondValue = second

    buf.append(if (hourValue < 10) "0" else "").append(hourValue)
        .append(if (minuteValue < 10) ":0" else ":").append(minuteValue)
    if (secondValue > 0) {
        buf.append(if (secondValue < 10) ":0" else ":").append(secondValue)
    }
    return buf.toString()
}
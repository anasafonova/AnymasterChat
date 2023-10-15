package com.dzaigames.anymasterchat.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDateTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    return format.format(date)
}
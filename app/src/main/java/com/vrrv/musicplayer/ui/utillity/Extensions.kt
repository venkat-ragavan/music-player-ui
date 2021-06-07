package com.vrrv.musicplayer.ui.utillity

import java.util.*

fun String.getYear(): String {
    return this.takeLast(4)
}

fun getGreetingText(): String {
    val cal: Calendar = Calendar.getInstance()
    cal.time = Date()
    return when (cal.get(Calendar.HOUR_OF_DAY)) {
        in 0..3 -> "Enjoy Your Night"
        in 4..11 -> "Good Morning"
        in 12..15 -> "Good Noon"
        in 16..20 -> "Good Evening"
        in 21..24 -> "Enjoy Your Night"
        else -> "Welcome!"
    }
}
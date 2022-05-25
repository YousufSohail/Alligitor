package com.yousufsohail.alligitor.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    fun longToDate(long: Long): Date {
        return Date(long)
    }

    fun dateToLong(date: Date): Long {
        return date.time / 1000 // return seconds
    }

    // Ex: "2022-05-24T13:46:19Z"
    fun dateToString(date: Date): String {
        return sdf.format(date)
    }

    fun stringToDate(string: String): Date {
        return sdf.parse(string) ?: throw NullPointerException("Could not convert date string to Date object.")
    }

    fun createTimestamp(): Date {
        return Date()
    }
}

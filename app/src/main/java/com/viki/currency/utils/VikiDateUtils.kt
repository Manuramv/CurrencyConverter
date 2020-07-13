package com.viki.currency.utils

import java.text.SimpleDateFormat
import java.util.*

//Date utils to conver the current date
object VikiDateUtils {
    fun getCurrentTime(): String {
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance()
        val sdf = SimpleDateFormat("dd MMM, hh:mm:ss")
        val formatedDate = sdf.format(date)
        return formatedDate
    }
}
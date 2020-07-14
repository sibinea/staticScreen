package com.sibin.eezzylifeapp.extension

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getDay1LetterName(date: Date): String =
        SimpleDateFormat("EEEEE", Locale.getDefault()).format(date)


    fun getDayNumber(date: Date): String =
        SimpleDateFormat("dd", Locale.getDefault()).format(date)

}
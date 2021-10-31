package tech.salvatore.livro_android_kotlin_paulo_salvatore.utils

import java.text.DateFormat
import java.util.*

object DateUtils {
    fun currentTimestamp(): String =
        DateFormat.getTimeInstance().apply {
            timeZone = TimeZone.getTimeZone("gmt")
        }.format(Date())
}

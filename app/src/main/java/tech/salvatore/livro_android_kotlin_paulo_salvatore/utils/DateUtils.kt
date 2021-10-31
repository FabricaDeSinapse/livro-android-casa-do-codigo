package tech.salvatore.livro_android_kotlin_paulo_salvatore.utils

import java.sql.Timestamp

object DateUtils {
    fun currentTimestamp(): Long =
        Timestamp(System.currentTimeMillis()).time / 1000
}

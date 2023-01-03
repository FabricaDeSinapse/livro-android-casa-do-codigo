package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain

import kotlin.math.floor
import kotlin.math.pow

object LevelFormula {
    fun getExperience(level: Long): Long {
        if (level == 1L) {
            return 0L
        }

        val formula = level.toDouble().pow(2.0) + level + 50

        return floor(formula).toLong()
    }
}

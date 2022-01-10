package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain

import tech.salvatore.livro_android_kotlin_paulo_salvatore.config.Config
import tech.salvatore.livro_android_kotlin_paulo_salvatore.utils.DateUtils
import kotlin.math.min

data class Creature(
    val number: Long,
    val name: String,

    val imageUrl: String,

    val legendary: Boolean = false,

    val level: Long = 1,

    val experience: Long = 0,

    val type1: CreatureType? = null,
    val type2: CreatureType? = null,

    val strength: Int = 0,

    val humor: Int = 0,

    val lastFeed: Long = 0,
    val lastTrain: Long = 0,
    val lastPlay: Long = 0,

    val evolveTo: Creature? = null,

    val canInteract: Boolean = true,

    val isKnown: Boolean = true,
) {
    // Experience

    val experienceToNextLevel: Long
        get() = LevelFormula.getExperience(level + 1)

    val experienceToNextLevelPercentage: Int
        get() = (experience * 100 / experienceToNextLevel).toInt()

    // Status

    private val hungry: Int
        get() = foodPercentage * Config.maxHungry / 100

    // Status Last Times

    private val secondsSinceLastFeed: Long
        get() = DateUtils.currentTimestamp - lastFeed

    private val secondsSinceLastTrain: Long
        get() = DateUtils.currentTimestamp - lastTrain

    private val secondsSinceLastPlay: Long
        get() = DateUtils.currentTimestamp - lastPlay

    // Status percentages

    val foodPercentage: Int
        get() = min(secondsSinceLastFeed * 100 / (Config.maxHungry * 60), 100L).toInt()

    val strengthPercentage: Int
        get() = strength * 100 / Config.maxStrength

    val humorPercentage: Int
        get() = humor * 100 / Config.maxHumor

    // Actions available

    val canEvolve: Boolean get() = canInteract && evolveTo != null && strength == 5 && humor == 5

    val canFeed: Boolean get() = canInteract && hungry == 4 || hungry == 5
    val canTrain: Boolean get() = canInteract && strength < 5 && secondsSinceLastTrain > Config.delayBeforeCanTrain
    val canPlay: Boolean get() = canInteract && humor < 5 && secondsSinceLastPlay > Config.delayBeforeCanPlay
}

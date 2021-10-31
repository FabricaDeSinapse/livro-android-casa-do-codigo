package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain

import tech.salvatore.livro_android_kotlin_paulo_salvatore.config.Config
import tech.salvatore.livro_android_kotlin_paulo_salvatore.utils.DateUtils
import kotlin.math.min

data class Creature(
    val number: Long,
    val name: String,

    val imageUrl: String,

    val type1: CreatureType? = null,
    val type2: CreatureType? = null,

    // TODO: Sobe 1 sempre que treina, só pode treinar 1x a cada 2 minutos
    val strength: Int = 0,

    // TODO: Sobe 1 sempre que brinca, só pode brincar 1x a cada 3 minutos
    val humor: Int = 0,

    val lastFeed: Long = 0,
    val lastTrain: Long = 0,
    val lastPlay: Long = 0,

    val evolveTo: Creature? = null,
    val users: List<User> = emptyList()
) {
    private val hungry: Int
        get() = foodPercentage * Config.maxHungry / 100

    private val secondsSinceLastFeed: Long
        get() = DateUtils.currentTimestamp() - lastFeed

    val foodPercentage: Int
        get() = min(secondsSinceLastFeed * 100 / (Config.maxHungry * 60), 100L).toInt()

    val strengthPercentage: Int
        get() = strength * 100 / Config.maxStrength

    val humorPercentage: Int
        get() = humor * 100 / Config.maxHumor

    val canFeed: Boolean get() = hungry == 4 || hungry == 5
    val canTrain: Boolean get() = strength < 5
    val canPlay: Boolean get() = humor < 5
}

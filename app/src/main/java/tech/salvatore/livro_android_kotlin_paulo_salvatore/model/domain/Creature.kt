package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain

import tech.salvatore.livro_android_kotlin_paulo_salvatore.config.Config

data class Creature(
    val number: Long,
    val name: String,

    val imageUrl: String,

    val type1: CreatureType? = null,
    val type2: CreatureType? = null,

    // TODO: Talvez remover hungry e usar apenas o lastFeed para definir
    // TODO: Botão só aparece quando está com 4 ou 5 e sempre zera a fome
    val hungry: Int = 0,

    // TODO: Sobe 1 sempre que treina, só pode treinar 1x a cada 2 minutos
    val strength: Int = 0,

    // TODO: Sobe 1 sempre que brinca, só pode brincar 1x a cada 3 minutos
    val humor: Int = 0,

    val lastFeed: Int = 0,
    val lastTrain: Int = 0,
    val lastPlay: Int = 0,

    val evolveTo: Creature? = null,
    val users: List<User> = emptyList()
) {
    val canFeed: Boolean get() = hungry > 0
    val canTrain: Boolean get() = strength < 5
    val canPlay: Boolean get() = humor < 5

    // TODO: Improve
    val hungryPercent: Int
        get() = if (hungry > 0) {
            hungry * Config.hungryPercentagePerPoint
        } else {
            0
        }
}

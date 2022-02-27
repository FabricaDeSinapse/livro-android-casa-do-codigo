package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreaturesRepository @Inject constructor() {
    val creatures: List<Creature>

    init {
        val creature1 = Creature(1, "Java", "https://i.imgur.com/PHfd1h2.png")
        val creature2 = Creature(2, "Kotlin", "https://i.imgur.com/EZ3zCBp.png")
        val creature3 = Creature(3, "Android", "https://i.imgur.com/r4UjONq.png")

        creatures = listOf(creature1, creature2, creature3)
    }
}

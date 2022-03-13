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
        val creature3 = Creature(3, "????", "https://i.imgur.com/rNKVxSt.png")
        val creature4 = Creature(4, "C", "https://i.imgur.com/iWeXxA9.png")
        val creature5 = Creature(5, "C++", "https://i.imgur.com/YYkSF5A.png")
        val creature6 = Creature(6, "????", "https://i.imgur.com/rNKVxSt.png")
        val creature7 = Creature(7, "????", "https://i.imgur.com/rNKVxSt.png")
        val creature8 = Creature(8, "????", "https://i.imgur.com/rNKVxSt.png")
        val creature9 = Creature(9, "????", "https://i.imgur.com/rNKVxSt.png")
        val creature10 = Creature(10, "????", "https://i.imgur.com/rNKVxSt.png")
        val creature11 = Creature(11, "????", "https://i.imgur.com/rNKVxSt.png")
        val creature12 = Creature(12, "????", "https://i.imgur.com/rNKVxSt.png")
        val creature13 = Creature(13, "????", "https://i.imgur.com/rNKVxSt.png")

        creatures = listOf(
            creature1,
            creature2,
            creature3,
            creature4,
            creature5,
            creature6,
            creature7,
            creature8,
            creature9,
            creature10,
            creature11,
            creature12,
            creature13,
        )
    }
}

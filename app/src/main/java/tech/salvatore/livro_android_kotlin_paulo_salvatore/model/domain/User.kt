package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain

data class User(
    val id: Int,
    val name: String,
    val creatures: List<Creature>
)

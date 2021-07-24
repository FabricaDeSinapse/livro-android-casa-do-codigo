package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain

data class Creature(
    val id: Int,
    val number: String,
    val name: String,
    val imageUrl: String,
    val hungry: Int,
    val strength: Int,
    val humor: Int,
    val evolveTo: Creature?,
    val users: List<User>
)

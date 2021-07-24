package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain

data class Creature(
    val id: Int,
    val number: Int,
    val name: String,
    val imageUrl: String,
    val hungry: Int,
    val strength: Int,
    val humor: Int,
    val evolveTo: Creature? = null,
    val users: List<User> = emptyList()
)

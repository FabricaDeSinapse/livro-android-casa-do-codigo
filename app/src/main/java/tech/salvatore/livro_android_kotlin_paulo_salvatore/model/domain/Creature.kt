package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain

data class Creature(
    val number: Int,
    val name: String,
    val imageUrl: String?,
    val hungry: Int = 0,
    val strength: Int = 0,
    val humor: Int = 0,
    val evolveTo: Creature? = null,
    val users: List<User> = emptyList()
)

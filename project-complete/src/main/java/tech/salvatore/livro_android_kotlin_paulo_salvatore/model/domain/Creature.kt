package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain

data class Creature(
    val number: Int,
    val name: String,
    val imageUrl: String,
    val isKnown: Boolean = false,
)

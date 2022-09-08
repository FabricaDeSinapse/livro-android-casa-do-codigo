package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain

data class User(
    val name: String,

    var hasCreatureAvailable: Boolean,
    val creatures: MutableList<Creature> = mutableListOf(),
)

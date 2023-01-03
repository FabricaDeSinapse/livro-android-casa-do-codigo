package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote.entity

data class CreatureApiResponse(
    val number: Int,
    val name: String,
    val image: String,
    val legendary: Boolean,
    val evolveTo: CreatureApiResponse?,
)

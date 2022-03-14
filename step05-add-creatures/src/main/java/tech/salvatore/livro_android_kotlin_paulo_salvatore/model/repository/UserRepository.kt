package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val creaturesRepository: CreaturesRepository,
) {
    val user = User("Paulo Salvatore", true)

    fun chooseCreature(): Creature? {
        if (!user.hasCreatureAvailable) {
            return null
        }

        user.hasCreatureAvailable = false

        val randomCreature = creaturesRepository.creatures.random()
        user.creatures.add(randomCreature)

        return randomCreature
    }
}

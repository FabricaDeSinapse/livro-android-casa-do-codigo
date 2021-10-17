package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.UserCreatureLocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCreatureRepository @Inject constructor(
    private val localDataSource: UserCreatureLocalDataSource,
    private val userRepository: UserRepository,
    private val creaturesRepository: CreatureRepository
) {
    fun chooseCreature() {
        val user = userRepository.user.value
        val newCreaturesAvailable = user?.newCreaturesAvailable ?: 0

        if (newCreaturesAvailable > 0) {
            // TODO: Sort random number
            val randomCreature = 1L

            localDataSource.create(randomCreature)
        }
    }
}

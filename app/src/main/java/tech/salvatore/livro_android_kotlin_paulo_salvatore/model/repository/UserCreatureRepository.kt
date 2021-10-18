package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.UserCreatureLocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCreatureRepository @Inject constructor(
    private val localDataSource: UserCreatureLocalDataSource,
    private val creaturesRepository: CreatureRepository
) {
    fun addRandomCreature() {
        val randomCreature = 1L

        // TODO: get random creature
//        val creaturesLevel1 = creaturesRepository.creaturesLevel1.subscribe {
//            it
//        }

        localDataSource.create(randomCreature)
    }
}

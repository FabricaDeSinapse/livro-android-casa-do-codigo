package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import io.reactivex.rxjava3.core.Single
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.UserCreatureLocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCreatureRepository @Inject constructor(
    private val localDataSource: UserCreatureLocalDataSource,
    private val creaturesRepository: CreatureRepository
) {
    fun addRandomCreature(userId: Long): Single<Creature> =
        Single.fromObservable(creaturesRepository.creaturesLevel1)
            .flatMap {
                val randomCreature = it.random()

                localDataSource.create(userId, randomCreature.number)
            }
}

package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.UserCreatureLocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCreatureRepository @Inject constructor(
    private val localDataSource: UserCreatureLocalDataSource,
    private val creaturesRepository: CreatureRepository
) {
    fun addRandomCreature(userId: Long): Flowable<Creature> =
        creaturesRepository
            .creaturesLevel1
            .toFlowable(BackpressureStrategy.LATEST)
            .take(1)
            .flatMapSingle {
                val randomCreature = it.random()

                localDataSource.create(userId, randomCreature.number)
            }
}

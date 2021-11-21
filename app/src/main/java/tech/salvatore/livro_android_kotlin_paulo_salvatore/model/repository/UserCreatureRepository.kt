package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import io.reactivex.rxjava3.core.Observable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.UserCreatureLocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCreatureRepository @Inject constructor(
    private val localDataSource: UserCreatureLocalDataSource,
    private val creaturesRepository: CreaturesRepository
) {
    fun addRandomCreature(userId: Long): Observable<Creature> =
        creaturesRepository.creaturesLevel1
            .skipWhile {
                it.count() == 0
            }
            .map {
                it.random()
            }
            .flatMapSingle {
                localDataSource.create(userId, it.number)
            }
}

package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import tech.salvatore.livro_android_kotlin_paulo_salvatore.config.Config
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.UserCreatureLocalDataSource
import tech.salvatore.livro_android_kotlin_paulo_salvatore.utils.DateUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCreatureRepository @Inject constructor(
        private val localDataSource: UserCreatureLocalDataSource,
) {
    fun create(userId: Long, creatureNumber: Long): Single<Creature> =
            localDataSource.create(userId, creatureNumber)
                    .subscribeOn(Schedulers.io())

    fun findByUserIdAndCreatureNumber(userId: Long, creatureNumber: Long): Single<Creature> =
            localDataSource.findByUserIdAndCreatureNumber(userId, creatureNumber)
                    .subscribeOn(Schedulers.io())

    fun feed(userId: Long, creature: Creature): Single<Creature> =
            Single
                    .just(creature)
                    .map {
                        it.copy(
                                experience = it.experience + Config.experienceOnFeed,
                                lastFeed = DateUtils.currentTimestamp,
                        )
                    }
                    .flatMap {
                        localDataSource.update(userId, it)
                    }
                    .subscribeOn(Schedulers.io())
}

package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import tech.salvatore.livro_android_kotlin_paulo_salvatore.config.Config
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.UserCreatureLocalDataSource
import tech.salvatore.livro_android_kotlin_paulo_salvatore.utils.DateUtils
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.min

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
                    lastFeed = DateUtils.currentTimestamp,
                )
            }
            .map {
                it.addExperience(Config.experienceOnFeed)
            }
            .flatMap {
                localDataSource.update(userId, it)
            }
            .subscribeOn(Schedulers.io())

    fun train(userId: Long, creature: Creature): Single<Creature> =
        Single
            .just(creature)
            .map {
                it.copy(
                    strength = min(it.strength + 1, Config.maxStrength),
                    lastTrain = DateUtils.currentTimestamp,
                )
            }
            .map {
                it.addExperience(Config.experienceOnTrain)
            }
            .flatMap {
                localDataSource.update(userId, it)
            }
            .subscribeOn(Schedulers.io())

    fun play(userId: Long, creature: Creature): Single<Creature> =
        Single
            .just(creature)
            .map {
                it.copy(
                    humor = min(it.humor + 1, Config.maxHumor),
                    lastPlay = DateUtils.currentTimestamp,
                )
            }
            .map {
                it.addExperience(Config.experienceOnPlay)
            }
            .flatMap {
                localDataSource.update(userId, it)
            }
            .subscribeOn(Schedulers.io())

    private fun Creature.addExperience(amount: Long): Creature {
        val creature = if (experience + amount >= experienceToNextLevel) {
            copy(
                experience = (experience + amount) - experienceToNextLevel,
                level = level + 1
            )
        } else {
            copy(
                experience = experience + amount
            )
        }

        if (creature.experience >= creature.experienceToNextLevel) {
            return creature.addExperience(creature.experience - creature.experienceToNextLevel)
        }

        return creature
    }

    fun evolve(userId: Long, creature: Creature): Single<Creature> {
        if (creature.evolveTo == null) {
            return Single.never()
        }

        return Single
            .just(creature)
            .map {
                it.copy(
                    canInteract = false,
                )
            }
            .flatMap {
                localDataSource.update(userId, it)
            }
            .flatMap {
                localDataSource.create(userId, creature.evolveTo.number)
            }
            .map {
                creature.evolveTo.copy(
                    experience = it.experience,
                    level = it.level,
                )
            }
            .flatMap {
                localDataSource.update(userId, it)
            }
            .subscribeOn(Schedulers.io())
    }
}

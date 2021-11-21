package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local

import io.reactivex.rxjava3.core.Single
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.AppDatabase
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.UserCreatureDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserCreatureEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCreatureLocalDataSource @Inject constructor(
        db: AppDatabase,
        private val creatureLocalDataSource: CreatureLocalDataSource
) {
    private val userCreatureDao: UserCreatureDao = db.userCreatureDao()

    fun create(userId: Long, creatureNumber: Long): Single<Creature> =
            Single
                    .just(
                            getUserCreatureEntity(userId, creatureNumber)
                    )
                    .flatMap {
                        userCreatureDao.insert(it)
                    }
                    .flatMap {
                        findById(it)
                    }

    private fun findById(userCreatureId: Long): Single<Creature> =
            userCreatureDao
                    .findById(userCreatureId)
                    .flatMap {
                        toCreatureDomain(it)
                    }

    fun findByUserIdAndCreatureNumber(userId: Long, creatureNumber: Long): Single<Creature> =
            userCreatureDao
                    .findByUserIdAndCreatureNumber(userId, creatureNumber)
                    .flatMap {
                        toCreatureDomain(it)
                    }

    fun update(userId: Long, creature: Creature): Single<Creature> =
            userCreatureDao
                    .findByUserIdAndCreatureNumber(userId, creature.number)
                    .flatMap { userCreatureEntity ->
                        Single
                                .just(userCreatureEntity)
                                .map {
                                    it.copy(
                                            strength = creature.strength,
                                            humor = creature.humor,
                                            lastFeed = creature.lastFeed,
                                            lastTrain = creature.lastTrain,
                                            lastPlay = creature.lastPlay,
                                    )
                                }
                                .flatMap {
                                    userCreatureDao.update(it)
                                }
                                .map {
                                    toCreatureDomain(userCreatureEntity, creature)
                                }
                    }

    // Mapper methods

    private fun getUserCreatureEntity(userId: Long, creatureNumber: Long) =
            UserCreatureEntity(
                    userId = userId,
                    creatureNumber = creatureNumber,
                    strength = 0,
                    humor = 0
            )

    fun toCreatureDomain(userCreatureEntity: UserCreatureEntity): Single<Creature> =
            with(userCreatureEntity) {
                return creatureLocalDataSource
                        .findByNumber(this.creatureNumber)
                        .map {
                            toCreatureDomain(this, it)
                        }
            }

    private fun toCreatureDomain(userCreatureEntity: UserCreatureEntity, creature: Creature): Creature =
            with(userCreatureEntity) {
                return Creature(
                        number = this.creatureNumber,
                        name = creature.name,
                        imageUrl = creature.imageUrl,
                        level = creature.level,
                        experience = creature.experience,
                        strength = this.strength,
                        humor = this.humor,
                        lastFeed = this.lastFeed,
                        lastTrain = this.lastTrain,
                        lastPlay = this.lastPlay
                )
            }
}

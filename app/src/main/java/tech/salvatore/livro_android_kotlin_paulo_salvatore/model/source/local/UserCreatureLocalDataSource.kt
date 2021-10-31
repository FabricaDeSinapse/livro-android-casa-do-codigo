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
                    Creature(
                        number = this.creatureNumber,
                        name = it.name,
                        imageUrl = it.imageUrl,
                        strength = this.strength,
                        humor = this.humor,
                        lastFeed = this.lastFeed,
                        lastTrain = this.lastTrain,
                        lastPlay = this.lastPlay
                    )
                }
        }
}

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

    fun create(userId: Long, creatureNumber: Long): Single<Creature> {
        val userCreatureEntity = UserCreatureEntity(
            userId = userId,
            creatureNumber = creatureNumber,
            hungry = 5,
            strength = 0,
            humor = 0
        )

        return userCreatureDao
            .insert(userCreatureEntity)
            .flatMap { newUserCreatureId ->
                findById(newUserCreatureId)
            }
    }

    private fun findById(userCreatureId: Long): Single<Creature> {
        return userCreatureDao
            .findById(userCreatureId)
            .flatMap { toDomain(it) }
    }

    fun toDomain(userCreatureEntity: UserCreatureEntity): Single<Creature> = with(userCreatureEntity) {
        return creatureLocalDataSource
            .findByNumber(this.creatureNumber)
            .map {
                Creature(
                    number = this.creatureNumber,
                    name = it.name,
                    imageUrl = it.imageUrl,
                    hungry = this.hungry,
                    strength = this.strength,
                    humor = this.humor,
                    lastFeed = this.lastFeed,
                    lastTrain = this.lastTrain,
                    lastPlay = this.lastPlay
                )
            }
    }
}

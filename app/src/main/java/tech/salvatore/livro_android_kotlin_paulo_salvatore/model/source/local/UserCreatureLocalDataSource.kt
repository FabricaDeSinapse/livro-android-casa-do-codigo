package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local

import io.reactivex.rxjava3.core.Flowable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.AppDatabase
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.UserCreatureDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserCreatureEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCreatureLocalDataSource @Inject constructor(
    db: AppDatabase
) {
    private val userCreatureDao: UserCreatureDao = db.userCreatureDao()

    fun create(creatureNumber: Long): Flowable<Creature> {
        // TODO: Hungry, Strength and Humor should be random

        val userCreatureEntity = UserCreatureEntity(
            userId = 0,
            creatureNumber = creatureNumber,
            hungry = 1,
            strength = 2,
            humor = 3
        )

        return userCreatureDao.insert(userCreatureEntity).flatMapPublisher { newUserCreatureId ->
            userCreatureDao.findById(newUserCreatureId).map { it.toDomain() }
        }
    }

    companion object {
        fun UserCreatureEntity.toDomain(): Creature {
            return Creature(
                number = this.creatureNumber,
                name = "TODO: Get from FK",
                imageUrl = "TODO: Get from FK",
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

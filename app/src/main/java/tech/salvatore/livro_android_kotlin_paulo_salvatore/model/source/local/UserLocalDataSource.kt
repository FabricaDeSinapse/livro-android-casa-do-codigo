package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local

import io.reactivex.rxjava3.core.Flowable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.User
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.datastore.UserSessionManager
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.AppDatabase
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.UserDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserCreatureEntity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserEntity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserEntityWithUserCreatureEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor(
    db: AppDatabase,
    userSessionManager: UserSessionManager
) {
    private val userDao: UserDao = db.userDao()

    val activeUser: Flowable<User> =
        userSessionManager.userSession.flatMap {
            if (it.activeUser == 0L) {
                create()
            } else {
                findById(it.activeUser)
            }
        }

    private fun create(): Flowable<User> {
        val userEntity = UserEntity(name = "Username")

        return userDao.insert(userEntity).flatMapPublisher { newUserId ->
            userDao.findById(newUserId).map { it.toDomain() }
        }
    }

    private fun findById(id: Long): Flowable<User> {
        return userDao
            .findById(id)
            .map { it.toDomain() }
    }

    // Mapper methods

    private fun UserEntity.toDomain(): User {
        return User(
            id = id!!,
            name = name,
            creatures = emptyList()
        )
    }

    private fun UserEntityWithUserCreatureEntity.toDomain(): User {
        return User(
            id = this.user.id!!,
            name = this.user.name,
            creatures = this.userCreatures.map { it.toDomain() }
        )
    }

    private fun UserCreatureEntity.toDomain(): Creature {
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

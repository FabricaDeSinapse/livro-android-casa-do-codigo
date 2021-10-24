package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.User
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.datastore.UserSessionManager
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.AppDatabase
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.UserDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserEntity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserEntityWithUserCreatureEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor(
    db: AppDatabase,
    userSessionManager: UserSessionManager,
    private val userCreatureLocalDataSource: UserCreatureLocalDataSource
) {
    private val userDao: UserDao = db.userDao()

    val activeUser: Flowable<User> =
        userSessionManager.userSession.flatMapSingle {
            when (it.activeUser) {
                null -> {
                    create()
                }

                else -> {
                    findById(it.activeUser)
                }
            }
        }

    private fun findById(id: Long): Single<User> =
        userDao
            .findById(id)
            .flatMap { it.toDomain() }

    private fun create(): Single<User> =
        Single
            .just(UserEntity(name = "Username", newCreaturesAvailable = 1))
            .flatMap { userEntity ->
                userDao.insert(userEntity)
                    .flatMap { newUserId ->
                        findById(newUserId)
                    }
            }

    fun update(user: User): Flowable<User> {
        val userEntity = user.fromDomain()

        return Flowable.just(userEntity)
            .flatMapSingle {
                userDao.update(it)
            }
            .flatMapSingle {
                findById(user.id)
            }
    }

    // Mapper methods

    private fun User.fromDomain(): UserEntity {
        return UserEntity(
            id = id,
            name = name,
            newCreaturesAvailable = newCreaturesAvailable
        )
    }

    private fun UserEntity.toDomain(): User {
        return User(
            id = id!!,
            name = name,
            // TODO: Fill user creatures
            creatures = emptyList(),
            newCreaturesAvailable = newCreaturesAvailable
        )
    }

    private fun UserEntityWithUserCreatureEntity.toDomain(): Single<User> {
        return Flowable.fromIterable(this.userCreatures)
            .flatMapSingle { userCreatureLocalDataSource.toDomain(it) }
            .toList()
            .map { creatures ->
                User(
                    id = this.user.id!!,
                    name = this.user.name,
                    creatures = creatures,
                    newCreaturesAvailable = this.user.newCreaturesAvailable
                )
            }
    }
}

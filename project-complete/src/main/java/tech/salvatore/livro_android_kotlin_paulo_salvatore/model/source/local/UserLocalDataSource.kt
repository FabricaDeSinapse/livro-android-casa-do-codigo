package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
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
    private val userSessionManager: UserSessionManager,
    private val userCreatureLocalDataSource: UserCreatureLocalDataSource
) {
    private val userDao: UserDao = db.userDao()

    val activeUser: Flowable<User> =
        userSessionManager.userSession
            .distinctUntilChanged { old, new ->
                old.activeUser != null && old.activeUser == new.activeUser
            }
            .flatMap {
                when (it.activeUser) {
                    null -> {
                        create().toFlowable()
                    }

                    else -> {
                        findByIdFlowable(it.activeUser)
                    }
                }
            }

    private fun findById(id: Long): Single<User> =
        Single.just(id)
            .flatMap {
                userDao.findById(id)
            }
            .flatMap {
                it.toDomain()
            }

    private fun findByIdFlowable(id: Long) =
        userDao
            .findByIdFlowable(id)
            .flatMap {
                it.toDomain().toFlowable()
            }

    private fun create(name: String = "Username") =
        Single
            .just(
                UserEntity(name = name, newCreaturesAvailable = 1)
            )
            .flatMap {
                userDao.insert(it)
            }
            .flatMap {
                userSessionManager.register(it)
            }
            .map {
                it.activeUser!!
            }
            .flatMap {
                findById(it)
            }

    fun update(user: User): Single<User> =
        Single
            .just(user)
            .map {
                it.fromDomain()
            }
            .flatMap {
                userDao.update(it)
            }
            .flatMap {
                findById(user.id)
            }

    // Mapper methods

    private fun User.fromDomain() =
        UserEntity(
            id = id,
            name = name,
            newCreaturesAvailable = newCreaturesAvailable
        )

    private fun UserEntity.toDomain(creatures: List<Creature>) =
        User(
            id = id!!,
            name = name,
            creatures = creatures,
            newCreaturesAvailable = newCreaturesAvailable
        )

    private fun UserEntityWithUserCreatureEntity.toDomain() =
        Flowable
            .fromIterable(this.userCreatures)
            .flatMapSingle {
                userCreatureLocalDataSource.toCreatureDomain(it)
            }
            .toList()
            .map {
                this.user.toDomain(it)
            }
}

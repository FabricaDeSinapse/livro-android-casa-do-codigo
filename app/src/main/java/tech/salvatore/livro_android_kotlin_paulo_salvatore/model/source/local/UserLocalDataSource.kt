package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local

import android.app.Application
import io.reactivex.rxjava3.core.Flowable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.User
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.AppDatabase
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.UserDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserCreatureEntity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserEntity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserEntityWithUserCreatureEntity

// TODO: Replace with Singleton
class UserLocalDataSource(application: Application) {
    private val db: AppDatabase = AppDatabase.getDb(application)

    private val userDao: UserDao = db.userDao()

    init {
//        GlobalScope.launch {
//            userDao.insert(
//                UserEntity(
//                    0,
//                    "Paulo",
//                )
//            ).subscribe {
//                Log.d("USER", "User inserted")
//            }
//
//            userDao.insertUserCreature(
//                UserCreatureEntity(
//                    0,
//                    1,
//                    1,
//                    2,
//                    3,
//                    4
//                )
//            ).subscribe {
//                Log.d("USER_CREATURE", "UserCreature inserted")
//            }
//        }
    }

//    val countUsers: Flowable<Int> = userDao.count()

//    val users: Flowable<List<User>> =
//        userDao
//            .findAll()
//            .map { userEntityList ->
//                userEntityList.map { userEntity ->
//                    userEntity.toDomain()
//                }
//            }

    val user: Flowable<User> =
        userDao
            .findActiveUser()
            .map { it.toDomain() }

//    fun insert(user: List<User>): Completable {
//        val userEntities = user.map { it.fromDomain() }
//
//        return userDao.insertAll(*userEntities.toTypedArray())
//    }

    //    private fun User.fromDomain(): UserEntity {
//        return UserEntity(
//            number = number,
//            name = name,
//            imageUrl = imageUrl,
//            evolveToNumber = evolveTo?.number
//        )
//    }

    private fun UserEntity.toDomain(): User {
        return User(
            id = id,
            name = name,
            creatures = emptyList()
        )
    }

    private fun UserEntityWithUserCreatureEntity.toDomain(): User {
        return User(
            id = this.user.id,
            name = this.user.name,
            creatures = this.userCreatures.map {it.toDomain()}
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

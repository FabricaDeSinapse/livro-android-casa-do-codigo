package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserCreatureEntity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserEntity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserEntityWithUserCreatureEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun findAll(): Flowable<List<UserEntity>>

    @Transaction
    @Query("SELECT * FROM user LIMIT 1")
    fun findActiveUser(): Flowable<UserEntityWithUserCreatureEntity>

    @Insert(onConflict = REPLACE)
    fun insert(user: UserEntity): Completable

    @Insert(onConflict = REPLACE)
    fun insertUserCreature(user: UserCreatureEntity): Completable
}

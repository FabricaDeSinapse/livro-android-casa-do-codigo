package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserEntity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserEntityWithUserCreatureEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun findAll(): Flowable<List<UserEntity>>

    @Transaction
    @Query("SELECT * FROM user WHERE id = :id")
    fun findById(id: Long): Single<UserEntityWithUserCreatureEntity>

    @Transaction
    @Query("SELECT * FROM user WHERE id = :id")
    fun findByIdFlowable(id: Long): Flowable<UserEntityWithUserCreatureEntity>

    @Insert(onConflict = REPLACE)
    fun insert(user: UserEntity): Single<Long>

    @Update
    fun update(user: UserEntity): Single<Int>
}

package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import io.reactivex.rxjava3.core.Single
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserCreatureEntity

@Dao
interface UserCreatureDao {
    @Transaction
    @Query("SELECT * FROM user_creature WHERE id = :id")
    fun findById(id: Long): Single<UserCreatureEntity>

    @Transaction
    @Query("SELECT * FROM user_creature WHERE userId = :userId AND creatureNumber = :creatureNumber")
    fun findByUserIdAndCreatureNumber(
        userId: Long,
        creatureNumber: Long
    ): Single<UserCreatureEntity>

    @Insert(onConflict = REPLACE)
    fun insert(userCreature: UserCreatureEntity): Single<Long>

    @Update
    fun update(userCreature: UserCreatureEntity): Single<Int>
}

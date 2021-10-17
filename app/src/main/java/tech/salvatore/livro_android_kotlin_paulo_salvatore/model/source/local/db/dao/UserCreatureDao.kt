package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserCreatureEntity

@Dao
interface UserCreatureDao {
    @Transaction
    @Query("SELECT * FROM user_creature WHERE id = :id")
    fun findById(id: Long): Flowable<UserCreatureEntity>

    @Insert(onConflict = REPLACE)
    fun insert(userCreature: UserCreatureEntity): Single<Long>
}

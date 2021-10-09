package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.CreatureEntity

@Dao
interface CreatureDao {
    @Query("SELECT * FROM creature")
    suspend fun findAll(): List<CreatureEntity>

    @Query("SELECT * FROM creature WHERE id IN (:creatureIds)")
    suspend fun findAllByIds(creatureIds: IntArray): List<CreatureEntity>

    @Query(
        "SELECT * FROM creature WHERE number LIKE :number LIMIT 1"
    )
    suspend fun findByNumber(number: Int): CreatureEntity

    @Insert
    suspend fun insertAll(vararg creatures: CreatureEntity)

    @Delete
    suspend fun delete(creature: CreatureEntity)
}

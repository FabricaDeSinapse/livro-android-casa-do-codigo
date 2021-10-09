package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.CreatureEntity

@Dao
interface CreatureDao {
    @Query("SELECT * FROM creature")
    fun loadAllCreatures(): Array<CreatureEntity>
}

package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity

import androidx.room.Entity

@Entity(primaryKeys = ["creatureId", "userId"])
data class CreatureUserCrossRef(
    val creatureId: Long,
    val userId: Long
)

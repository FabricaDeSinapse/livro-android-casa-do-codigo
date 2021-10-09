package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UserWithCreature(
    @Embedded val user: UserEntity,

    @Relation(
        parentColumn = "userId",
        entityColumn = "creatureId",
        associateBy = Junction(CreatureUserCrossRef::class)
    )
    val creatures: List<CreatureEntity>
)

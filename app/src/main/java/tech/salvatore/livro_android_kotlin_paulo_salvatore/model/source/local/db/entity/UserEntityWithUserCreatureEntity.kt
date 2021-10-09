package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserEntityWithUserCreatureEntity(
    @Embedded
    val user: UserEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val userCreatures: List<UserCreatureEntity>
)

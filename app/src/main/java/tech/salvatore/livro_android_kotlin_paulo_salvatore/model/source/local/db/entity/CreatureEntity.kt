package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "creature", indices = [
        Index(value = ["number"], unique = true),
        Index(value = ["name"], unique = true),
        Index(value = ["evolveToNumber"], unique = true)
    ]
)
data class CreatureEntity(
    @PrimaryKey
    val number: Long,

    val name: String,

    val imageUrl: String,

    val legendary: Boolean,

    val evolveToNumber: Long?,
)

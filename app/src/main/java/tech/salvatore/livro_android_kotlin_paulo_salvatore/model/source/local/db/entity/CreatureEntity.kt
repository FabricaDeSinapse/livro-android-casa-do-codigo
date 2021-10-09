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
    val number: Int,

    val name: String,

    val imageUrl: String?,

    val hungry: Int,

    val strength: Int,

    val humor: Int,

    val evolveToNumber: Int?,

    var lastFeed: Int = 0,

    var lastTrain: Int = 0,

    var lastPlay: Int = 0,
)

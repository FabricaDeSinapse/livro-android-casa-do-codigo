package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "creature")
data class CreatureEntity(
    @PrimaryKey
    val id: Int,

    val number: Int,

    val name: String,

    val imageUrl: String,

    val hungry: Int,

    val strength: Int,

    val humor: Int,

    val evolveTo: Int?,

    var lastFeed: Int,

    var lastTrain: Int,

    var lastPlay: Int,
)

package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CreatureEntity(
    @PrimaryKey
    val id: Int,

    val number: Int,

    val name: String,

    val imageUrl: String,

    val hungry: Int,

    val strength: Int,

    val humor: Int,

    @Embedded val evolveTo: CreatureEntity?,

    var lastFeed: Int,

    var lastTrain: Int,

    var lastPlay: Int,
)

package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,

    val name: String,
)

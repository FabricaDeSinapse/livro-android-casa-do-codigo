package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user", indices = [
        Index(value = ["name"], unique = true),
    ]
)
data class UserEntity(
    @PrimaryKey
    val id: Long? = null,

    val name: String,

    val newCreaturesAvailable: Int,
)

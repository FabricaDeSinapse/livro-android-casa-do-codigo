package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.CreatureDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.UserCreatureDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.UserDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.CreatureEntity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserCreatureEntity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserEntity

@Database(
    entities = [
        CreatureEntity::class,
        UserEntity::class,
        UserCreatureEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun creatureDao(): CreatureDao

    abstract fun userCreatureDao(): UserCreatureDao
}

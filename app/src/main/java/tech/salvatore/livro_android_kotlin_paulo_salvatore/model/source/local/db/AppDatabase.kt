package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.CreatureDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.UserDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.CreatureEntity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.UserEntity

@Database(entities = [CreatureEntity::class, UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun creatureDao(): CreatureDao

    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDb(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "app-database"
                ).build()
            }

            return instance!!
        }
    }
}

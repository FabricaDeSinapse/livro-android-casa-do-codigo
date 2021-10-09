package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import android.app.Application
import kotlinx.coroutines.runBlocking
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.AppDatabase
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.CreatureDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.CreatureEntity

class CreatureRepository(application: Application) {
    private val creatureDao: CreatureDao

    val creatures: List<CreatureEntity>

    init {
        val db = AppDatabase.getDb(application)

        creatureDao = db.creatureDao()

        creatures = runBlocking { creatureDao.findAll() }
    }

    fun insert(creature: CreatureEntity) {

    }
}

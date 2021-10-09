package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local

import android.app.Application
import io.reactivex.rxjava3.core.Flowable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.AppDatabase
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.CreatureDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.CreatureEntity

class CreatureLocalDataSource(application: Application) {
    private val db: AppDatabase = AppDatabase.getDb(application)

    private val creatureDao: CreatureDao = db.creatureDao()

    val countCreatures: Flowable<Int> = creatureDao.count()

    val creatures: Flowable<List<CreatureEntity>> = creatureDao.findAll()

    //    private suspend fun loadData(): List<CreatureEntity> {
//        val count = creatureDao.count()
//
//        if (count > 0) {
//            // Get from DB
//            Log.d("CREATURE_LOCAL_DS", "Get From DB")
//        } else {
//            // Get from API
//            Log.d("CREATURE_LOCAL_DS", "Get From Api")
//        }
//
//        return creatureDao.findAll()
//    }
}

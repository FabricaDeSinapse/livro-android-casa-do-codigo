package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local

import android.app.Application
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.AppDatabase
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.CreatureDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.CreatureEntity

class CreatureLocalDataSource(application: Application) {
    private val db: AppDatabase = AppDatabase.getDb(application)

    private val creatureDao: CreatureDao = db.creatureDao()

    val countCreatures: Flowable<Int> = creatureDao.count()

    val creatures: Flowable<List<Creature>> =
        creatureDao
            .findAll()
            .map { creatureEntityList ->
                creatureEntityList.map { creatureEntity ->
                    creatureEntity.toDomain()
                }
            }

    fun insert(creature: List<Creature>): Completable {
        val creatureEntities = creature.map { it.fromDomain() }

        return creatureDao.insertAll(*creatureEntities.toTypedArray())
    }

    private fun Creature.fromDomain(): CreatureEntity {
        return CreatureEntity(
            number = number,
            name = name,
            imageUrl = imageUrl,
            hungry = hungry,
            strength = strength,
            humor = humor,
            evolveToNumber = evolveTo?.number
        )
    }

    private fun CreatureEntity.toDomain(): Creature {
        return Creature(
            number = number,
            name = name,
            imageUrl = imageUrl,
            hungry = hungry,
            strength = strength,
            humor = humor,
            // TODO: EvolveTo
        )
    }
}

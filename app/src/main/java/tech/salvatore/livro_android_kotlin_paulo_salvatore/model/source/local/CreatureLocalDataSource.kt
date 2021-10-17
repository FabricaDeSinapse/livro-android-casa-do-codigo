package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.AppDatabase
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.CreatureDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.CreatureEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreatureLocalDataSource @Inject constructor(
    db: AppDatabase
) {
    private val creatureDao: CreatureDao = db.creatureDao()

    val countCreatures: Flowable<Long> = creatureDao.count()

    private val creaturesEntities = creatureDao.findAll()

    val creatures: Flowable<List<Creature>> =
        creaturesEntities
            .flatMap { Flowable.fromIterable(it) }
            .flatMap { it.toDomain() }
            .toList()
            .toFlowable()

    fun insert(creature: List<Creature>): Completable {
        val creatureEntities = creature.map { it.fromDomain() }

        return creatureDao.insertAll(*creatureEntities.toTypedArray())
    }

    private fun findByNumber(number: Long): Flowable<Creature> {
        return creatureDao
            .findByNumber(number)
            .flatMap { it.toDomain() }
    }

    private fun findByEvolveToNumber(number: Long): Flowable<Creature> {
        return creatureDao
            .findByEvolveToNumber(number)
            .flatMap { it.toDomain() }
    }

    // Mappers

    private fun Creature.fromDomain(): CreatureEntity {
        return CreatureEntity(
            number = number,
            name = name,
            imageUrl = imageUrl,
            evolveToNumber = evolveTo?.number
        )
    }

    private fun CreatureEntity.toDomain(): Flowable<Creature> {
        val evolveFromFlowable =
            if (this.evolveToNumber != null)
                findByEvolveToNumber(this.number)
            else
                Flowable.never()

        val evolveToFlowable =
            if (this.evolveToNumber != null)
                findByNumber(this.evolveToNumber)
            else
                Flowable.never()

        return Flowable.combineLatest(
            evolveFromFlowable,
            evolveToFlowable,
            { evolveFrom, evolveTo ->
                Creature(
                    number = number,
                    name = name,
                    imageUrl = imageUrl,
                    evolveFrom = evolveFrom,
                    evolveTo = evolveTo
                )
            }
        )
    }
}

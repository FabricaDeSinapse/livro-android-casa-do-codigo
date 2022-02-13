package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.AppDatabase
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.dao.CreatureDao
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.CreatureEntity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.utils.Optional
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreatureLocalDataSource @Inject constructor(
    db: AppDatabase
) {
    private val creatureDao: CreatureDao = db.creatureDao()

    private val creaturesEntities = creatureDao.findAll()

    val creatures: Flowable<List<Creature>> =
        creaturesEntities
            .flatMap {
                Flowable
                    .fromIterable(it)
                    .flatMapSingle { creatureEntity ->
                        creatureEntity.toDomain()
                    }
                    .toList()
                    .toFlowable()
            }

    fun insert(creature: List<Creature>): Completable {
        val creatureEntities = creature.map { it.fromDomain() }

        return creatureDao.insertAll(*creatureEntities.toTypedArray())
    }

    fun findByNumber(number: Long): Single<Creature> {
        return creatureDao
            .findByNumber(number)
            .flatMap { it.toDomain() }
    }

    // Mappers methods

    private fun Creature.fromDomain() =
        CreatureEntity(
            number = number,
            name = name,
            imageUrl = imageUrl,
            legendary = legendary,
            evolveToNumber = evolveTo?.number,
        )

    private fun CreatureEntity.toDomain(): Single<Creature> {
        val evolveToNumber: Single<Optional<Creature>> =
            if (this.evolveToNumber != null) {
                findByNumber(this.evolveToNumber)
                    .map { Optional(it) }
            } else {
                Single.just(Optional(null))
            }

        return evolveToNumber.map { evolveTo ->
            Creature(
                number = number,
                name = name,
                imageUrl = imageUrl,
                legendary = legendary,
                evolveTo = evolveTo.value,
            )
        }
    }
}

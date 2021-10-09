package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import android.app.Application
import io.reactivex.rxjava3.subjects.ReplaySubject
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.CreatureLocalDataSource
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.entity.CreatureEntity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote.CreatureRemoteDataSource

// TODO: Maybe should be singleton
class CreatureRepository(application: Application) {
    // TODO: Maybe should be singleton
    private val localDataSource = CreatureLocalDataSource(application)

    // TODO: Maybe should be singleton
    private val remoteDataSource = CreatureRemoteDataSource

    val creatures = ReplaySubject.create<List<Creature>>()

    init {
        // Load Creatures From Local Data Source
        localDataSource.creatures.map {
            // Convert CreatureEntity to Creature
            emptyList<Creature>()
        }.subscribe {
            // Update creatures' ReplaySubject
            creatures.onNext(it)
        }

        // Load Creatures From Local Remote Source
        remoteDataSource.creatures.map {
            // Convert CreatureApiResponse to Creature
            emptyList<Creature>()
            // TODO: Insert creatures on database (by number)
        }.subscribe {
            // Update creatures' ReplaySubject
            creatures.onNext(it)
        }
    }

    /*
    val creatures2: Flowable<List<Creature>>? by lazy {
        val a = localDataSource.countCreatures.flatMap { count ->
            val c = Observable.just(emptyList<Creature>()).toFlowable(BackpressureStrategy.LATEST)

            if (count > 0) {
                // Return creatures from DB
                // Ask API for loading creatures (if new creatures are available)
            } else {
                // Ask API for loading creatures
            }

            c
//            if (count > 0) {
//                localDataSource.creatures.flatMap { creaturesEntities ->
//                    creaturesEntities.map {
//                        Creature()
//                    }
//                }
//            } else {
//                emptyList<Creature>()
//            }
        }

        val b = Observable.just(emptyList<Creature>()).toFlowable(BackpressureStrategy.LATEST)

        b
    }
    */

//    private val creatureDao: CreatureDao

//    val creatures: List<Creature>

    /*
    Se a lista de criaturas não tiver disponível, precisamos buscar de algum dataSource
    Se o LocalDataSource responder que não tem nada, ele precisa precisar no RemoteDataSource
    Iniciamos a chamada do RemoteDataSource e aguardamos
    Quem está precisando lá na frente precisa exibir uma mensagem de carregamento (talvez uma Skeleton Screen)
    Assim que a API retornar, inserimos os datos no LocalDataSource e passamos a obter as informações dele
    */

    init {
//        val db = AppDatabase.getDb(application)
//
//        creatureDao = db.creatureDao()
//
//        val count = runBlocking { creatureDao.count() }
//
//        if (count > 0) {
//            // Get from DB
//        } else {
//            // Get from API
//        }
//
//        val creaturesFromDb = runBlocking { creatureDao.findAll() }
    }

    fun insert(creature: CreatureEntity) {
    }
}

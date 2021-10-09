package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import android.app.Application
import android.util.Log
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

    val creatures: ReplaySubject<List<Creature>> = ReplaySubject.create(1)

    init {
        // Load Creatures From Local Data Source
        localDataSource.creatures.map {
            // Convert CreatureEntity to Creature
            emptyList<Creature>()
        }.subscribe {
            Log.d("CREATURE", "Finish loading local data source.")

            // Update creatures' ReplaySubject
            creatures.onNext(it)
        }

        // Load Creatures From Local Remote Source
        remoteDataSource.creatures.map {
            // Convert CreatureApiResponse to CreatureEntity
            emptyList<Creature>()
            // TODO: Insert creatures on database (by number)
        }.subscribe {
            Log.d("CREATURE", "Finish loading remote data source.")

            // Update creatures' ReplaySubject
            creatures.onNext(it)
        }
    }

    fun insert(creature: CreatureEntity) {
    }
}

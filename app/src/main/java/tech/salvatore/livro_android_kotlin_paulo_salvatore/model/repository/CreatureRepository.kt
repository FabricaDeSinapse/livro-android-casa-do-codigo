package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import android.util.Log
import io.reactivex.rxjava3.subjects.ReplaySubject
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.CreatureLocalDataSource
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote.CreatureRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreatureRepository @Inject constructor(
    localDataSource: CreatureLocalDataSource,
    remoteDataSource: CreatureRemoteDataSource
) {
    val creatures: ReplaySubject<List<Creature>> = ReplaySubject.create(1)

    val creaturesLevel1 = creatures.map { list ->
        list.filter {
            it.evolveFrom != null
        }
    }

    init {
        // Load Creatures From Local Data Source
        localDataSource.creatures.doOnNext {
            Log.d("CREATURE", "Finish loading local data source: ${it.count()}")
        }.subscribe {
            // Update creatures' ReplaySubject
            creatures.onNext(it)
        }

        // Load Creatures From Local Remote Source
        remoteDataSource.creatures.doOnNext {
            Log.d("CREATURE", "Finish loading remote data source: ${it.count()}")
        }.flatMapCompletable {
            localDataSource.insert(it)
        }.subscribe {
            Log.d("CREATURE", "Creatures were added.")
        }
    }
}

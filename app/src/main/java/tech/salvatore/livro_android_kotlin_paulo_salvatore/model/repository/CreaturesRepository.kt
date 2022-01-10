package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.ReplaySubject
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.CreatureLocalDataSource
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote.CreatureRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreaturesRepository @Inject constructor(
    private val localDataSource: CreatureLocalDataSource,
    remoteDataSource: CreatureRemoteDataSource
) {
    val creatures: ReplaySubject<List<Creature>> = ReplaySubject.create(1)

    val creaturesLevel1: Observable<List<Creature>> =
        creatures
            .map { creatures ->
                creatures
                    .filter {
                        !it.legendary
                    }
                    .mapNotNull { creature ->
                        val doesntEvolve = creatures.find {
                            it.evolveTo?.number == creature.number
                        }

                        if (doesntEvolve == null) creature else null
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

    fun findByNumber(number: Long): Observable<Creature> =
        localDataSource.findByNumber(number)
            .toObservable()
            .subscribeOn(Schedulers.io())
}

package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.ReplaySubject
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote.CreatureRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreaturesRepository @Inject constructor(
    remoteDataSource: CreatureRemoteDataSource
) {
    val creatures: ReplaySubject<List<Creature>> = ReplaySubject.create(1)

    init {
        remoteDataSource.creatures
            .doOnNext {
                Log.d("CREATURES_REPOSITORY", "${it.size} creatures were loaded from API.")
            }
            .doOnError {
                Log.e("CREATURES_REPOSITORY", "Error loading creatures from API.", it)
            }
            .subscribe {
                creatures.onNext(it)
            }
    }

    fun findCreature(number: Int): Observable<Creature> =
        creatures.map { list ->
            list.find { it.number == number }
        }
}

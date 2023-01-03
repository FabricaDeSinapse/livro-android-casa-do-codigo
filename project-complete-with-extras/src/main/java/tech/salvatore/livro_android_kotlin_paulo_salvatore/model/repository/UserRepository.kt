package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.User
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.UserLocalDataSource
import tech.salvatore.livro_android_kotlin_paulo_salvatore.utils.Optional
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val userCreatureRepository: UserCreatureRepository,
    private val creaturesRepository: CreaturesRepository,
) {
    val user: ReplaySubject<User> = ReplaySubject.create(1)

    val creaturesOwnByUser: Observable<List<Optional<out Creature>>> =
        user
            .flatMap { user ->
                creaturesRepository.creatures.flatMap {
                    Observable.fromIterable(it)
                        .map { creature ->
                            if (user.creatures
                                    .map { it.number }
                                    .contains(creature.number)
                            ) {
                                Optional(creature)
                            } else {
                                Optional(null)
                            }
                        }
                        .toList()
                        .toObservable()
                }
            }

    val onChooseCreature: PublishSubject<Creature> = PublishSubject.create()

    init {
        // Load User From Local Data Source
        localDataSource.activeUser.doOnNext {
            Log.d("USER", "Load Active User: ${it.id}")
        }.subscribe {
            // Update user's ReplaySubject
            user.onNext(it)
        }
    }

    fun chooseCreature(): Observable<Creature> =
        user
            .take(1)
            .filter {
                it.newCreaturesAvailable > 0
            }
            .map {
                it.copy(newCreaturesAvailable = it.newCreaturesAvailable - 1)
            }
            .flatMapSingle {
                localDataSource.update(it)
            }
            .flatMap {
                addRandomCreature()
            }
            .doOnNext {
                onChooseCreature.onNext(it)
            }
            .subscribeOn(Schedulers.io())

    private fun addRandomCreature(): Observable<Creature> =
        user
            .take(1)
            .flatMap { user ->
                creaturesRepository.creaturesLevel1
                    .skipWhile {
                        it.count() == 0
                    }
                    .map {
                        it.random()
                    }
                    .flatMapSingle {
                        userCreatureRepository.create(user.id, it.number)
                    }
            }
            .subscribeOn(Schedulers.io())
}

package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import android.util.Log
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.ReplaySubject
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.User
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.UserLocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val userCreatureRepository: UserCreatureRepository
) {
    val user: ReplaySubject<User> = ReplaySubject.create(1)

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
            .flatMapMaybe { userValue ->
                val newCreaturesAvailable = userValue.newCreaturesAvailable

                if (newCreaturesAvailable > 0) {
                    val userId = userValue.id

                    Single.just(userValue)
                        .flatMap {
//                            it.newCreaturesAvailable--

                            localDataSource.update(userValue)
                        }.flatMap {
                            userCreatureRepository.addRandomCreature(userId)
                        }.toMaybe()
                } else {
                    Maybe.empty()
                }
            }
            .subscribeOn(Schedulers.io())
}

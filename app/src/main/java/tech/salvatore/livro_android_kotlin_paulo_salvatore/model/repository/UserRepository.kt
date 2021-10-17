package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import android.util.Log
import io.reactivex.rxjava3.subjects.ReplaySubject
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.User
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.UserLocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    localDataSource: UserLocalDataSource,
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

    fun chooseCreature() {
        val newCreaturesAvailable = user.value?.newCreaturesAvailable ?: 0

        if (newCreaturesAvailable > 0) {
            userCreatureRepository.addRandomCreature()
        }
    }
}

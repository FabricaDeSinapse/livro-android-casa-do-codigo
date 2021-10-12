package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import android.app.Application
import android.util.Log
import io.reactivex.rxjava3.subjects.ReplaySubject
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.User
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.UserLocalDataSource

// TODO: Maybe should be singleton
class UserRepository(application: Application) {
    private val creaturesRepository = CreatureRepository(application)

    // TODO: Maybe should be singleton
    private val localDataSource = UserLocalDataSource(application)

    val user: ReplaySubject<User> = ReplaySubject.create(1)

    init {
        creaturesRepository.creatures.subscribe {
        }

        // Load Users From Local Data Source
        localDataSource.user.doOnNext {
            Log.d("USER", "Finish loading local data source: $it")
        }.subscribe {
            // Update users' ReplaySubject
//            users.onNext(it)
        }
    }
}

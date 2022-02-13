package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.datastore

import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.rxjava3.RxDataStore
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSessionManager @Inject constructor(
    private val sessionDataStore: RxDataStore<Preferences>
) {
    private object SessionKeys {
        val ACTIVE_USER = longPreferencesKey("ACTIVE_USER")
    }

    val userSession: Flowable<UserSession> =
        sessionDataStore.data()
            .map {
                it.toSession()
            }

    fun register(userId: Long): Single<UserSession> =
        sessionDataStore.updateDataAsync {
            val mutablePreferences: MutablePreferences = it.toMutablePreferences()

            mutablePreferences[SessionKeys.ACTIVE_USER] = userId

            Single.just(mutablePreferences)
        }.map {
            it.toSession()
        }

    private fun Preferences.toSession(): UserSession {
        val activeUser = this[SessionKeys.ACTIVE_USER]

        return UserSession(activeUser)
    }
}

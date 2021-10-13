package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder
import androidx.datastore.rxjava3.RxDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSessionManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private var sessionDataStore: RxDataStore<Preferences> =
        RxPreferenceDataStoreBuilder(context, "SESSION_DATA").build()

    private object SessionKeys {
        val ACTIVE_USER = longPreferencesKey("ACTIVE_USER")
    }

    var userSession: Flowable<UserSession> = sessionDataStore.data().map { preferences ->
        val activeUser = preferences[SessionKeys.ACTIVE_USER] ?: 0L

        UserSession(activeUser)
    }
}

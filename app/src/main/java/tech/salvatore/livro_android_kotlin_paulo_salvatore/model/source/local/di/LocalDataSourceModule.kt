@file:Suppress("unused")

package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.local.db.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app-database"
        ).build()
    }

//    @Singleton
//    @Provides
//    fun provideRxDataStore(@ApplicationContext context: Context): RxDataStore<Preferences> {
//        return RxPreferenceDataStoreBuilder(context, "SESSION_DATA").build()
//    }
}

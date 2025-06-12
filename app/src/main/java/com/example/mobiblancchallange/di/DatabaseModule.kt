package com.example.mobiblancchallange.di

import android.content.Context
import androidx.room.Room
import com.example.mobiblancchallange.data.local.LocalDatabase
import com.example.mobiblancchallange.data.local.dao.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideUserDatabase(
        @ApplicationContext context: Context
    ): LocalDatabase {
        return Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            LocalDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUsersDao(database: LocalDatabase): UsersDao {
        return database.usersDao()
    }
}
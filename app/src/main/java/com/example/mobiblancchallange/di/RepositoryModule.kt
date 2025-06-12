package com.example.mobiblancchallange.di

import com.example.mobiblancchallange.data.repository.AppRepositoryImpl
import com.example.mobiblancchallange.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        appRepositoryImpl: AppRepositoryImpl
    ): AppRepository
}
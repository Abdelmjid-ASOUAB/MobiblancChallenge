package com.example.mobiblancchallange.domain.repository

import com.example.mobiblancchallange.data.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getAllUsersLocal(): Flow<List<UserEntity>>

    suspend fun fetchUsersAndCache()
}
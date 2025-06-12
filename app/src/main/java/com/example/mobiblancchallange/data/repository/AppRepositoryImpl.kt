package com.example.mobiblancchallange.data.repository

import com.example.mobiblancchallange.data.local.dao.UsersDao
import com.example.mobiblancchallange.data.local.entities.UserEntity
import com.example.mobiblancchallange.data.remote.ApiService
import com.example.mobiblancchallange.data.remote.mappers.toEntity
import com.example.mobiblancchallange.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.jvm.Throws

class AppRepositoryImpl @Inject constructor(
    private val userDao: UsersDao,
    private val apiService: ApiService
) : AppRepository {
    override fun getAllUsersLocal(): Flow<List<UserEntity>> = userDao.getAllUsers()

    @Throws(Exception::class)

    override suspend fun fetchUsersAndCache() {
        val response = apiService.getAllUsers()
        userDao.deleteAllUsers() // remove existing users before inserting new ones to keep the cache fresh
        userDao.insertAll(response.map { it.toEntity() })
    }
}

package com.example.mobiblancchallange.data.repository

import com.example.mobiblancchallange.DummyData
import com.example.mobiblancchallange.data.local.dao.UsersDao
import com.example.mobiblancchallange.data.remote.ApiService
import com.example.mobiblancchallange.data.remote.mappers.toEntity
import com.example.mobiblancchallange.domain.repository.AppRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AppRepositoryTest {
    private lateinit var userDao: UsersDao
    private lateinit var apiService: ApiService
    private lateinit var appRepository: AppRepository

     @Before
    fun setup() {
        userDao = mockk(relaxed = true)
        apiService = mockk(relaxed = true)
        appRepository = AppRepositoryImpl(userDao, apiService)
    }

    @Test
    fun `getAllUsersLocal should return flow from dao`() {
        // Given
        val expectedFlow = flowOf(DummyData.userList.map { it.toEntity() })

        every { userDao.getAllUsers() } returns expectedFlow

        // When
        val result = appRepository.getAllUsersLocal()


        // Then
        assert(result == expectedFlow)
        verify { userDao.getAllUsers() }

    }

    @Test
    fun `fetchUsersAndCache should fetch from api and save to database`() = runBlocking {
        // Given
        val userList = DummyData.userList
        coEvery { apiService.getAllUsers() } returns userList

        // When
        appRepository.fetchUsersAndCache()

        // Then
        coVerify { userDao.insertAll(userList.map { it.toEntity() }) }
    }

}
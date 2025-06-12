package com.example.mobiblancchallange.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mobiblancchallange.DummyData
import com.example.mobiblancchallange.data.remote.mappers.toEntity
import com.example.mobiblancchallange.domain.repository.AppRepository
import com.example.mobiblancchallange.presentation.ui.LoadingState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: AppRepository
    private lateinit var viewModel: ViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should call getAllUsersLocal and fetchUsersFormAPi`() = testScope.runTest {
        // Given
        every { repository.getAllUsersLocal() } returns flowOf(DummyData.userList.map { it.toEntity() })
        coEvery { repository.fetchUsersAndCache() } returns Unit

        // When
        viewModel = ViewModel(repository)
        advanceUntilIdle()

        // Then
        coVerify { repository.fetchUsersAndCache() }
        coVerify { repository.getAllUsersLocal() }
    }

    @Test
    fun `fetchUsersFormAPi should update loading state to Loading then Success when successful`() = testScope.runTest {
        // Given
        every { repository.getAllUsersLocal() } returns flowOf(emptyList())
        coEvery { repository.fetchUsersAndCache() } returns Unit

        // When
        viewModel = ViewModel(repository)
        advanceUntilIdle()

        // Then
        val finalState = viewModel.uiState.value
        assertEquals(LoadingState.Success, finalState.loadingState)
    }

    @Test
    fun `fetchUsersFormAPi should update loading state to Error when exception occurs`() = testScope.runTest {
        // Given
        val errorMessage = "Network error"
        every { repository.getAllUsersLocal() } returns flowOf(emptyList())
        coEvery { repository.fetchUsersAndCache() } throws Exception(errorMessage)

        // When
        viewModel = ViewModel(repository)
        advanceUntilIdle()

        // Then
        val finalState = viewModel.uiState.value
        assertTrue(finalState.loadingState is LoadingState.Error)
        assertEquals(errorMessage, (finalState.loadingState as LoadingState.Error).message)
    }
}

package com.example.mobiblancchallange.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiblancchallange.domain.repository.AppRepository
import com.example.mobiblancchallange.presentation.ui.LoadingState
import com.example.mobiblancchallange.presentation.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        getAllUsersLocal()
        fetchUsersFormAPi()
    }


    private fun fetchUsersFormAPi() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(loadingState = LoadingState.Loading)
            }

            try {
                repository.fetchUsersAndCache()
                _uiState.update {
                    it.copy(loadingState = LoadingState.Success)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Error(
                            e.message ?: "Failed to load User data"
                        )
                    )
                }
            }
        }
    }

    fun getAllUsersLocal() {
        viewModelScope.launch {
            repository.getAllUsersLocal()
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            loadingState = LoadingState.Error(
                                e.message ?: "Unknown error occurred"
                            )
                        )
                    }
                }
                .collect { userList ->
                    _uiState.update {
                        it.copy(
                            loadingState = LoadingState.Success,
                            userList = userList
                        )
                    }
                }
        }
    }
}
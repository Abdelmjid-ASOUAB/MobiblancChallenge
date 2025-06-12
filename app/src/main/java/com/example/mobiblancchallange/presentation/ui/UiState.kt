package com.example.mobiblancchallange.presentation.ui

import com.example.mobiblancchallange.data.local.entities.UserEntity


sealed class LoadingState {
    data object Loading : LoadingState()
    data object Success : LoadingState()
    data class Error(val message: String) : LoadingState()
}


data class UiState(
    val loadingState: LoadingState = LoadingState.Loading,
    val userList: List<UserEntity> = emptyList()
)
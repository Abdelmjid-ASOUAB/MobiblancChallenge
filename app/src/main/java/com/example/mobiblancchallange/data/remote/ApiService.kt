package com.example.mobiblancchallange.data.remote

import com.example.mobiblancchallange.data.remote.response.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getAllUsers(): List<User>

    companion object {
        const val BASE_URL = "https://api.escuelajs.co/api/v1/"
    }
}
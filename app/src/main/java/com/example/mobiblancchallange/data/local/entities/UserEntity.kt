package com.example.mobiblancchallange.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val email: String,
    val name: String,
    val role: String,
    val avatar: String,
    val creationAt: String,
    val updatedAt: String
)
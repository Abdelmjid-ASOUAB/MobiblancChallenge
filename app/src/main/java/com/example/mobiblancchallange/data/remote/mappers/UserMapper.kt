package com.example.mobiblancchallange.data.remote.mappers

import com.example.mobiblancchallange.data.local.entities.UserEntity
import com.example.mobiblancchallange.data.remote.response.User


fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        email = this.email,
        name = this.name,
        role = this.role,
        avatar = this.avatar,
        creationAt = this.creationAt,
        updatedAt = this.updatedAt
    )
}
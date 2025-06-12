package com.example.mobiblancchallange

import com.example.mobiblancchallange.data.remote.response.User

object DummyData {
    val user1 = User(
        id = 1,
        email = "john@mail.com",
        password = "changeme",
        name = "Jhon",
        role = "customer",
        avatar = "https://i.imgur.com/LDOO4Qs.jpg",
        creationAt = "2025-06-12T02:20:22.000Z",
        updatedAt = "2025-06-12T02:20:22.000Z"
    )

    val user2 = User(
        id = 2,
        email = "maria@mail.com",
        password = "12345",
        name = "Maria",
        role = "customer",
        avatar = "https://i.imgur.com/DTfowdu.jpg",
        creationAt = "2025-06-12T02:20:22.000Z",
        updatedAt = "2025-06-12T02:20:22.000Z"
    )

    val user3 = User(
        id = 3,
        email = "admin@mail.com",
        password = "admin123",
        name = "Admin",
        role = "admin",
        avatar = "https://i.imgur.com/yhW6Yw1.jpg",
        creationAt = "2025-06-12T02:20:22.000Z",
        updatedAt = "2025-06-12T02:20:22.000Z"
    )

    val userList = listOf(user1, user2, user3)
}
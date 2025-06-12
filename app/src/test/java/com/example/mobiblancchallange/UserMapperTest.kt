package com.example.mobiblancchallange

import com.example.mobiblancchallange.data.remote.mappers.toEntity
import junit.framework.TestCase.assertTrue
import org.junit.Test

class UserMapperTest {

    @Test
    fun `converting user list to entity should not be empty`() {
        // When
        val result = DummyData.userList.map {
            it.toEntity()
        }

        // Then
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `converting user list to entity should have correct size`() {
        // When
        val result = DummyData.userList.map {
            it.toEntity()
        }

        // Then
        assertTrue(result.size == DummyData.userList.size)
    }
}
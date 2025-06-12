package com.example.mobiblancchallange.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobiblancchallange.data.local.entities.UserEntity
import com.example.mobiblancchallange.data.remote.response.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userList: List<UserEntity>)

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()
}
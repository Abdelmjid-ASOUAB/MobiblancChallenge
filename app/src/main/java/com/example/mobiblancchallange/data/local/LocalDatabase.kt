package com.example.mobiblancchallange.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobiblancchallange.data.local.dao.UsersDao
import com.example.mobiblancchallange.data.local.entities.UserEntity


@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao

    companion object {
        const val DATABASE_NAME = "local_db"
    }
}
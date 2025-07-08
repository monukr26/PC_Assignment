package com.example.mymovieapp.addusers

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [UserInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao
}
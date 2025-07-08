package com.example.mymovieapp.addusers

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.OnConflictStrategy
import com.example.mymovieapp.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserInfo)

    @Query("SELECT * FROM users ORDER BY id desc")
    fun getAllUsers(): Flow<List<UserInfo>>

    @Query("SELECT * FROM users where isSynced =0")
    suspend fun getUnsyncedUsers(): List<UserInfo>

    @Update
    suspend fun updateUser(user: UserInfo)
}
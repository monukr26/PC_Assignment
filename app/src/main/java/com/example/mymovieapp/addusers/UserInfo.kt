package com.example.mymovieapp.addusers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserInfo(
    @PrimaryKey(autoGenerate = true) val id : Int=0,
    val name : String,
    val email : String,
    val isSynced: Boolean = false
)

package com.example.mymovieapp

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data") val users: List<User>,
    @SerializedName("page") val page : Int,
    @SerializedName("total_pages") val totalPages: Int

)

data class User(
    val id : Int,
    @SerializedName("first_name")val firstName : String,
    @SerializedName("last_name")val lastName : String,
    val avatar : String
)

package com.example.mymovieapp


import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi  {
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): UserResponse
}
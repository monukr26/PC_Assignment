package com.example.mymovieapp.addusers

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("users")
    suspend fun syncUser(@Body user: UserInfo): Response<Unit>
}
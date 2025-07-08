package com.example.mymovieapp.movielist

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("trending/movie/day")
    suspend fun getTrendingMovies(@Query("language") language:String="en-us",
                                 @Query("page") page: Int,
                                 @Query("api_key") apiKey: String
    ): MovieResponse
}
package com.example.mymovieapp.moviedetails

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailApi {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId:Int,
                                @Query("api_key") apiKey: String): MovieData
}
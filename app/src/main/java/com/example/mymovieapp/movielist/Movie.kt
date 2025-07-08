package com.example.mymovieapp.movielist

import com.google.gson.annotations.SerializedName
import javax.inject.Singleton

data class MovieResponse(
    @SerializedName("results") val result: List<Movie>
)

data class Movie(
    val id : Int,
    val title : String,
    @SerializedName("release_date") val releaseDate : String?,
    @SerializedName("poster_path") val posterPath : String?
)

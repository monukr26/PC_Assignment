package com.example.mymovieapp.movielist

import com.google.gson.annotations.SerializedName
import javax.inject.Singleton

data class MovieResponse(
    @SerializedName("results") val result: List<Movie>
)

data class Movie(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("poster_path") val releaseDate : String,
    @SerializedName("release_date") val posterPath : String
)

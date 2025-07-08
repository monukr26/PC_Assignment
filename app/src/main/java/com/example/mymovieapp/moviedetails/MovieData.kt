package com.example.mymovieapp.moviedetails

import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("overview") val description : String,
    @SerializedName("release_date") val releaseDate : String,
    @SerializedName("poster_path") val posterPath : String

)

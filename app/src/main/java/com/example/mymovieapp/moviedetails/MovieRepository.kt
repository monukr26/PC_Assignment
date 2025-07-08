package com.example.mymovieapp.moviedetails

import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieDetailApi
) {
    suspend fun getMovieDetails(movieId: Int): MovieData{
        return api.getMovieDetails(movieId, ConstApi.TMBD_api)
    }
}
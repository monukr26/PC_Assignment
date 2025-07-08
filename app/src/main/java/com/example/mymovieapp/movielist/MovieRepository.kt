package com.example.mymovieapp.movielist

import androidx.paging.Pager
import androidx.paging.PagingConfig
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepository @Inject constructor(
    private val api: MovieApi
) {
    fun getTrendingMovies(apiKey: String): Pager<Int, Movie>{
        return Pager(PagingConfig(pageSize = 20)){
            MoviePaging(api, apiKey)
        }
    }
}
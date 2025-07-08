package com.example.mymovieapp.movielist

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class MoviePaging (
    private val api : MovieApi,
    private val  apiKey : String
): PagingSource<Int, Movie>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?:1
        return try {

            val response = api.getTrendingMovies(page = page, apiKey = apiKey)
            Log.d("MoviePaging", "API Response: ${response.result.size} movies")
            LoadResult.Page(
                data = response.result,
                prevKey = if(page==1) null else page -1,
                nextKey = if(response.result.isEmpty()) null else page+1

            )
        } catch (e: Exception){
            Log.e("MoviePaging", "Error loading movies", e)
            LoadResult.Error(e)


        }
    }
    override fun getRefreshKey(state: PagingState<Int, Movie>) = null

}
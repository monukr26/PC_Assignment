package com.example.mymovieapp.movielist


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {



    val movies = repo.getTrendingMovies("b705439a9877a211e234fcacd8ef5644")
        .flow
        .cachedIn(viewModelScope)
}

package com.example.mymovieapp.moviedetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository,
    savedStateHandle: SavedStateHandle

): ViewModel() {
    var movieDetails by mutableStateOf<MovieData?>(null)
        private set

    init {
        val movieId = savedStateHandle.get<Int>("movieId")
        if(movieId==null){
            Log.e("MovieDetailVM","ID is null")
        }
        else{
            viewModelScope.launch {
                try {
                    movieDetails = repository.getMovieDetails(movieId)
                } catch (e: Exception){
                    Log.e("MovieDetailVM", "Failed", e)
                }
            }

        }

    }
}
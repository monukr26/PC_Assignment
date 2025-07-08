package com.example.mymovieapp.moviedetails

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun MovieDetailScreen(movieId: Int,viewModel: MovieDetailViewModel= hiltViewModel()){
    Log.d("MovieDetailScreen", "MovieDetailScreen displayed for movieId: $movieId")
    val movie = viewModel.movieDetails

    if(movie==null){
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
                .background(color= Color.Yellow),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "About Movie",
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Column (modifier = Modifier.padding(top = 80.dp)){
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                    contentDescription = null,
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(14.dp))
                Text(text = movie?.title ?: "Loading", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Release: ${movie.releaseDate}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Description: ${movie.description}")
            }

        }

    }

}


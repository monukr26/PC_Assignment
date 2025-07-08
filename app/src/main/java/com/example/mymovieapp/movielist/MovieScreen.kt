package com.example.mymovieapp.movielist




import android.R.id.title
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage



@Composable
fun MovieScreen(userId: Int, viewModel: MovieViewModel=hiltViewModel(),
                onMovieClick: (Int)-> Unit){

    val movies = viewModel.movies.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 40.dp)
        .background(color= Color.Magenta),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Movie List",
            style = MaterialTheme.typography.titleLarge,
        )
    }
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.itemCount) { index ->
            val movie = movies[index]
            if (movie != null) {
                MovieCard(movie, onClick = { onMovieClick(movie.id) })
            }
        }

        if (movies.loadState.refresh is LoadState.Loading) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        if (movies.loadState.refresh is LoadState.Error) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(36.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error loading movies")

                }
            }
        }

        if (movies.loadState.append is LoadState.Loading) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(modifier = Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    }

}



@Composable
fun MovieCard(movie: Movie, onClick: () -> Unit) {
    Card (modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column( modifier = Modifier.padding(10.dp)) {
            val imageUrl = "https://image.tmdb.org/t/p/w185${movie.posterPath}"
            AsyncImage(
                model = imageUrl,
                contentDescription = movie.title,
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(6.dp))

            Column {
                Text(movie.title, fontWeight = FontWeight.Bold)
                Text("Release: ${movie.releaseDate}")
            }

        }



    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMovie(){
    MovieCard(
        movie = Movie(
            id = 1,
            title = "Interstellar",
            releaseDate = "2014-11-07",
            posterPath = "/poster.jpg"
        ),
        onClick = {}
    )
}


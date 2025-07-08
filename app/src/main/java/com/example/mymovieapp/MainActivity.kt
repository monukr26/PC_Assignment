package com.example.mymovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.mymovieapp.addusers.AddUserScreen
import com.example.mymovieapp.moviedetails.MovieDetailScreen
import com.example.mymovieapp.movielist.MovieScreen
import com.example.mymovieapp.ui.theme.MyMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMovieAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "tabs"){
        composable("tabs") {
            TabScreen(navController=navController)
        }

        composable("movies/{userId}"){
            backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()?:return@composable
                   MovieScreen(userId=userId, onMovieClick = { movieid ->
                    navController.navigate("movie_details/$movieid")

                })

        }
        composable("movie_details/{movieId}",
            arguments = listOf(navArgument("movieId"){ type = NavType.IntType })){
            val movieId = it.arguments?.getInt("movieId")?: 0
            MovieDetailScreen(movieId=movieId)

        }
    }
}


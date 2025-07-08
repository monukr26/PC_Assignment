package com.example.mymovieapp.movielist

import com.example.mymovieapp.addusers.UserApi
import com.example.mymovieapp.moviedetails.MovieDetailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MovieNetwork {
    @Provides
    @Singleton
    @Named("TMDB")
    fun provideTmdbRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(@Named("TMDB") retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDetailApi(@Named("TMDB") retrofit: Retrofit): MovieDetailApi {
        return retrofit.create(MovieDetailApi::class.java)
    }





}
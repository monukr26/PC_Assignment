package com.example.mymovieapp

import android.app.Application
import android.content.res.Configuration
import androidx.hilt.work.HiltWorkerFactory
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.google.firebase.inject.Provider
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyMovieApp : Application()




package com.example.mymovieapp.addusers

import android.view.View
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewUserViewModel @Inject constructor(
    private val repo: NewRepository
): ViewModel(){

    val allUsers = repo.getAllUsers().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    fun addUser(name: String, email:String){
        viewModelScope.launch {
            repo.insertUser(UserInfo(name = name, email = email, isSynced=false))
            enqueueSync()
        }
    }

    private fun enqueueSync(){
        val request = OneTimeWorkRequestBuilder<SyncWorker>().build()
        WorkManager.getInstance().enqueue(request)
    }
}
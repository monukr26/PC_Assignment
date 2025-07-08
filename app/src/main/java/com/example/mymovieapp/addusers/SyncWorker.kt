package com.example.mymovieapp.addusers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlin.coroutines.CoroutineContext
@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repo: NewRepository

): CoroutineWorker(context,params){

    override suspend fun doWork(): Result {
        val unsyncedUsers = repo.getUnsyncedUsers()

        if(unsyncedUsers.isEmpty()){
            return Result.success()
        }

        unsyncedUsers.forEach { user->
            try {
                val response = repo.syncUserToServer(user)
                if(response.isSuccessful){
                    repo.updateUser(user.copy(isSynced = true))

                } else{
                    return Result.retry()
                }


            }catch (e: Exception){
                return Result.retry()
            }
        }
        return Result.success()
    }
}
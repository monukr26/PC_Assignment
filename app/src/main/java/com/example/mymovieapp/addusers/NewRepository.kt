package com.example.mymovieapp.addusers

import com.example.mymovieapp.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewRepository @Inject constructor(
    private val dao: UserDao,
    private val api: UserApi

) {
    suspend fun insertUser(user: UserInfo) = dao.insertUser(user)

    fun getAllUsers(): Flow<List<UserInfo>> = dao.getAllUsers()

    suspend fun getUnsyncedUsers() = dao.getUnsyncedUsers()

    suspend fun updateUser(user: UserInfo) = dao.updateUser(user)

    suspend fun syncUserToServer(user: UserInfo): Response<Unit>{
        return api.syncUser(user)
    }
}
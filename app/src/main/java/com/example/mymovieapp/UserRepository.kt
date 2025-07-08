package com.example.mymovieapp


import androidx.paging.Pager
import androidx.paging.PagingConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val api: UserApi
) {
    fun getUsers(): Pager<Int, User> = Pager(
        config = PagingConfig(pageSize = 6),
        pagingSourceFactory = { UserPaging(api)}
    )
}
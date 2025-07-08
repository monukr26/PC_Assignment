package com.example.mymovieapp

import androidx.paging.PagingSource
import androidx.paging.PagingState

class UserPaging(
    private val api: UserApi
): PagingSource<Int, User>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: 1
            val response = api.getUsers(page)
            LoadResult.Page(
                data = response.users,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page < response.totalPages) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, User>): Int? = null

}
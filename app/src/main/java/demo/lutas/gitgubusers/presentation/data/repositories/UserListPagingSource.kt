package demo.lutas.gitgubusers.presentation.data.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import demo.lutas.gitgubusers.domain.data.entities.User
import demo.lutas.gitgubusers.domain.data.repositories.UserRepository
import demo.lutas.gitgubusers.presentation.data.remote.UserService

class UserListPagingSource(
    private val service: UserService,
    private val perPage: Int
) : PagingSource<Int, User>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, User> {
        try {
            val since = params.key ?: 0
            val response = service.getUsers(since, perPage)
            return LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = since + response.size
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
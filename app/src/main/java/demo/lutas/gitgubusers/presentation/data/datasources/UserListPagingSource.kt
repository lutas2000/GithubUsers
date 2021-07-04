package demo.lutas.gitgubusers.presentation.data.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import demo.lutas.gitgubusers.domain.data.entities.User
import demo.lutas.gitgubusers.presentation.data.remote.UserService

class UserListPagingSource(
    private val service: UserService,
    private val perPage: Int = 20
) : PagingSource<Int, User>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, User> {
        return try {
            val since = params.key ?: 0
            val response = service.getUsers(since, perPage)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = since + response.size
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
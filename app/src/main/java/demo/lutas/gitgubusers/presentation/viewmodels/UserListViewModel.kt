package demo.lutas.gitgubusers.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import demo.lutas.gitgubusers.presentation.data.remote.UserService
import demo.lutas.gitgubusers.presentation.data.repositories.UserListPagingSource

class UserListViewModel(private val userService: UserService): ViewModel() {
    private val perPage = 20

    val flow = Pager(
        PagingConfig(pageSize = perPage)
    ) {
        UserListPagingSource(userService, perPage)
    }.flow
        .cachedIn(viewModelScope)
}
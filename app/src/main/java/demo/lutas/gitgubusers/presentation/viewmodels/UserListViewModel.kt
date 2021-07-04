package demo.lutas.gitgubusers.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import demo.lutas.gitgubusers.domain.data.repositories.UserRepository
import demo.lutas.gitgubusers.presentation.data.remote.UserService
import demo.lutas.gitgubusers.presentation.data.datasources.UserListPagingSource

class UserListViewModel(userRepository: UserRepository): ViewModel() {
    private val perPage = 20
    val flow = userRepository.getUsers(perPage)
        .cachedIn(viewModelScope)
}